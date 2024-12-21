package com.kryptopass.nooro.core.local.datasource

import android.util.Log
import com.kryptopass.nooro.core.repository.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.local.room.WeatherDao
import com.kryptopass.nooro.core.local.room.WeatherEntity
import com.kryptopass.nooro.core.local.room.toEntity
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherLocalDataSource {

    override fun getCurrentWeather(name: String): Flow<Weather?> {
        Log.d(TAG, "FETCH WEATHER FOR CITY: $name")
        return weatherDao.getWeatherByCity(name).map { weatherEntity ->
            if (weatherEntity == null) {
                Log.e(TAG, "NO WEATHER ENTITY FOR: $name")
                null
            } else {
                val domainWeather = mapDomainWeather(weatherEntity)
                Log.d(TAG, "MAPPED WEATHER: $domainWeather")
                domainWeather
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addWeather(weather: Weather) {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "ADD WEATHER TO ROOM: $weather")
            val weatherEntity = weather.toEntity()
            Log.d(TAG, "MAPPED WEATHER ENTITY: $weatherEntity")
            weatherDao.insertWeather(weatherEntity)
            Log.d(TAG, "SUCCESS: ADDING TO ROOM.")
        }
    }

    private fun mapDomainWeather(weatherEntity: WeatherEntity): Weather {
        Log.d(TAG, "MAPPED WEATHER ENTITY: $weatherEntity")
        return Weather(
            current = Current(
                feelslikeC = weatherEntity.feelsLikeC ?: 0.0,
                feelslikeF = weatherEntity.feelsLikeF ?: 0.0,
                humidity = weatherEntity.humidity ?: 0,
                tempC = weatherEntity.tempC ?: 0.0,
                tempF = weatherEntity.tempF ?: 0.0,
                uv = weatherEntity.uv ?: 0.0
            ),
            location = Location(
                country = weatherEntity.country.orEmpty(),
                name = weatherEntity.name,
                region = weatherEntity.region.orEmpty()
            )
        )
    }

    companion object {
        private const val TAG = "WeatherLocalDataSourceImpl"
    }
}