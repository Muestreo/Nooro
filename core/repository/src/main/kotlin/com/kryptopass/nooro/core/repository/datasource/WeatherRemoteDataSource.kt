package com.kryptopass.nooro.core.repository.datasource

import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {
    fun getCurrentWeather(name: String): Flow<Weather>
}
