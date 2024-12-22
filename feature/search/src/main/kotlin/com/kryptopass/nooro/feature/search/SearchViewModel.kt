package com.kryptopass.nooro.feature.search

import android.util.Log
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
class SearchViewModel @Inject constructor(
    private val cityDataStore: CityDataStore,
    private val converter: SearchConverter,
    private val usecase: FetchWeatherByCityUseCase
) : MviViewModel<SearchModel, UiState<SearchModel>, SearchUiAction, UiSingleEvent>() {

    override fun initState(): UiState<SearchModel> = UiState.Loading

    override fun handleAction(action: SearchUiAction) {
        // NOTE: not needed now as SearchBar invokes fetching weather...
        //       use case for paging or list of cities
        when (action) {
            is SearchUiAction.Load -> loadWeather(action.name)
        }
    }

    fun loadWeather(name: String) {
        viewModelScope.launch {
            try {
                submitState(UiState.Loading)
                usecase.execute(FetchWeatherByCityUseCase.Request(name))
                    .map { converter.convert(it) }
                    .collect { submitState(it) }
            } catch (e: Exception) {
                submitState(UiState.Error("Failed to load weather data"))
                Log.e(TAG, "ERROR LOADING WEATHER: ${e.message}")
            }
        }
    }

    fun saveCity(city: String) {
        viewModelScope.launch {
            cityDataStore.saveCity(city)
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}
