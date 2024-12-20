package com.kryptopass.nooro.core.data.datasource

import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {
    fun getCurrentWeather(city: String): Flow<Weather>
}