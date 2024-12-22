package com.kryptopass.nooro.feature.home

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
class HomeViewModel @Inject constructor(
    private val cityDataStore: CityDataStore,
    private val converter: HomeConverter,
    private val usecase: FetchWeatherByCityUseCase
) : MviViewModel<HomeModel, UiState<HomeModel>, HomeUiAction, UiSingleEvent>() {

    override fun initState(): UiState<HomeModel> = UiState.Empty

    override fun handleAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnSearchBarEnterDoneItemClick -> {
                submitSingleEvent(
                    HomeUiSingleEvent.OpenSearchScreen(
                        action.city
                    )
                )
            }
        }
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                submitState(UiState.Loading)
                usecase.execute(FetchWeatherByCityUseCase.Request(city))
                    .map { converter.convert(it) }
                    .collect { result ->
                        submitState(result)
                    }
            } catch (e: Exception) {
                submitState(UiState.Error("NETWORK ERROR: Failed to load weather data"))
                Log.e(TAG, "ERROR LOADING WEATHER: ${e.message}")
            }
        }
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

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
