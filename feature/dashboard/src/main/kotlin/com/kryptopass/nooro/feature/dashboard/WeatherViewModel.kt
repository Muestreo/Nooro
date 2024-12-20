package com.kryptopass.nooro.feature.dashboard

import androidx.lifecycle.viewModelScope
import com.kryptopass.nooro.core.common.state.MviViewModel
import com.kryptopass.nooro.core.common.state.UiSingleEvent
import com.kryptopass.nooro.core.common.state.UiState
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.feature.dashboard.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val fetchWeatherByCityUseCase: FetchWeatherByCityUseCase,
    private val converter: WeatherConverter
) : MviViewModel<WeatherModel, UiState<WeatherModel>, WeatherUiAction, UiSingleEvent>() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    override fun initState(): UiState<WeatherModel> = UiState.Loading

    override fun handleAction(action: WeatherUiAction) {
        when (action) {
            is WeatherUiAction.Load -> {
                loadWeather(action.city)
            }
        }
    }

    private fun loadWeather(city: String) {
        viewModelScope.launch {
            fetchWeatherByCityUseCase.execute(FetchWeatherByCityUseCase.Request(city))
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}