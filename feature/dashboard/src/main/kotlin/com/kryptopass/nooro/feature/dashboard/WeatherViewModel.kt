package com.kryptopass.nooro.feature.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import com.kryptopass.nooro.feature.dashboard.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun fetchWeather(region: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                weatherRepository.getCurrentWeather(region).collect { response ->
                    Log.d(TAG, "RESPONSE: $response")
                    _uiState.value = WeatherUiState.Success(response)
                }
            } catch (e: Exception) {
                Log.e(TAG, "FAILED TO FETCH WEATHER DATA: ${e.message}")
                _uiState.value = WeatherUiState.Error("Failed to fetch weather data")
            }
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}