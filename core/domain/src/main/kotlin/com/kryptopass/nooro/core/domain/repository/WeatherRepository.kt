package com.kryptopass.nooro.core.domain.repository

import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(city: String): Flow<Weather>
}