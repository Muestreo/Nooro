package com.kryptopass.nooro.feature.dashboard.state

import com.kryptopass.nooro.core.network.model.WeatherResponse

sealed class WeatherUiState {
    data object Empty : WeatherUiState()
    data object Loading : WeatherUiState()
    data class Success(val weather: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}