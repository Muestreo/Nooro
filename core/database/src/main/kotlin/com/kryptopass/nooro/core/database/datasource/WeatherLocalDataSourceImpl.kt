package com.kryptopass.nooro.core.database.datasource

import com.kryptopass.nooro.core.data.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(

) : WeatherLocalDataSource {

    override fun getCurrentWeather(city: String): Flow<Weather> {
        TODO("Not yet implemented")
    }

    override suspend fun addCurrentWeather(weatherResponse: Weather) {
        TODO("Not yet implemented")
    }
}