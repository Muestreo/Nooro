package com.kryptopass.nooro.feature.dashboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.shared.common.state.MviViewModel
import com.kryptopass.nooro.shared.common.state.UiSingleEvent
import com.kryptopass.nooro.shared.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val converter: WeatherConverter,
    private val usecase: FetchWeatherByCityUseCase
): MviViewModel<WeatherModel, UiState<WeatherModel>, WeatherUiAction, UiSingleEvent>() {

    override fun initState(): UiState<WeatherModel> = UiState.Empty

    override fun handleAction(action: WeatherUiAction) {
        // NOTE: not needed now as SearchBar invokes fetching weather...
        //       use case for paging or list of cities
        when (action) {
            is WeatherUiAction.Load -> loadWeather(action.name)
        }
    }

    fun loadWeather(name: String) {
        viewModelScope.launch {
            try {
                usecase.execute(FetchWeatherByCityUseCase.Request(name))
                    .map { converter.convert(it) }
                    .collect { submitState(it) }
            } catch (e: Exception) {
                submitState(UiState.Error("Failed to load weather data"))
                Log.e(TAG, "ERROR LOADING WEATHER: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}