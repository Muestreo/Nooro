package com.kryptopass.nooro.feature.dashboard

import androidx.lifecycle.viewModelScope
import com.kryptopass.nooro.core.domain.repository.CityDataStore
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
    private val cityDataStore: CityDataStore,
    private val converter: WeatherConverter,
    private val usecase: FetchWeatherByCityUseCase
): MviViewModel<WeatherModel, UiState<WeatherModel>, WeatherUiAction, UiSingleEvent>() {

    override fun initState(): UiState<WeatherModel> = UiState.Empty

    override fun handleAction(action: WeatherUiAction) {
        // NOTE: not needed now as SearchBar invokes fetching weather...
        //       use case for paging or list of cities
    }

    fun loadPersistedCityWeather() {
        viewModelScope.launch {
            cityDataStore.getCity().collect { persistedCity ->
                if (persistedCity.isNullOrEmpty()) {
                    submitState(UiState.Empty)
                } else {
                    fetchWeather(persistedCity)
                }
            }
        }
    }

    private fun fetchWeather(city: String) {
        viewModelScope.launch {
            submitState(UiState.Loading) // Show loading state
            usecase.execute(FetchWeatherByCityUseCase.Request(city))
                .map { converter.convert(it) }
                .collect { result ->
                    submitState(result)
                }
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}