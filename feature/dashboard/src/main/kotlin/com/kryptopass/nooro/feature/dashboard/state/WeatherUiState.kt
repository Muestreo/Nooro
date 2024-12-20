package com.kryptopass.nooro.feature.dashboard.state

import com.kryptopass.nooro.core.domain.entity.Weather

sealed class WeatherUiState {
    data object Empty : WeatherUiState()
    data object Loading : WeatherUiState()
    data class Success(val weather: Weather) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}