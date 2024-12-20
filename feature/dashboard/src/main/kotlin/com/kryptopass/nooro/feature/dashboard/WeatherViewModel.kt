package com.kryptopass.nooro.feature.dashboard

import com.kryptopass.nooro.core.common.state.MviViewModel
import com.kryptopass.nooro.core.common.state.UiSingleEvent
import com.kryptopass.nooro.core.common.state.UiState
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.feature.dashboard.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val fetchWeatherByCityUseCase: FetchWeatherByCityUseCase,
    private val converter: WeatherConverter
) : MviViewModel<WeatherModel, UiState<WeatherModel>, WeatherUiAction, UiSingleEvent>() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    override fun handleAction(action: WeatherUiAction) {
        TODO("Not yet implemented")
    }

    override fun initState(): UiState<WeatherModel> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}