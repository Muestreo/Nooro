package com.kryptopass.nooro.core.database.datasource

import com.kryptopass.nooro.core.data.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.database.room.WeatherDao
import com.kryptopass.nooro.core.database.room.toDomain
import com.kryptopass.nooro.core.database.room.toEntity
import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherLocalDataSource {

    override fun getCurrentWeather(city: String): Flow<Weather> {
        return weatherDao.getWeatherByRegion(city).map { weatherEntity ->
            weatherEntity.toDomain()
        }
    }

    override suspend fun addCurrentWeather(weather: Weather) {
        val weatherEntity = weather.toEntity()
        weatherDao.insertWeather(weatherEntity)
    }
}