package com.kryptopass.nooro.core.data.repository

import com.kryptopass.nooro.core.network.model.WeatherResponse
import com.kryptopass.nooro.core.network.service.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeather(city: String): WeatherResponse {
        return weatherService.getCurrentWeather(
            apiKey = "ab4f0c764b034bc2a45175907241912", // BuildConfig.WEATHER_API_KEY,
            city = city
        )
    }
}