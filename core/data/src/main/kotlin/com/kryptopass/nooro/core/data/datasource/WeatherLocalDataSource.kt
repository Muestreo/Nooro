package com.kryptopass.nooro.core.data.datasource

import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {
    fun getCurrentWeather(name: String): Flow<Weather?>

    suspend fun addWeather(weather: Weather)
}