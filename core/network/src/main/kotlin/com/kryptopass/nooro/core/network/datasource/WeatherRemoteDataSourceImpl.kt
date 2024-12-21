package com.kryptopass.nooro.core.network.datasource

import android.util.Log
import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.network.BuildConfig
import com.kryptopass.nooro.core.network.model.toDomain
import com.kryptopass.nooro.core.network.service.WeatherService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRemoteDataSource {

    override fun getCurrentWeather(name: String): Flow<Weather> = flow {
        try {
            Log.d(TAG, "FETCH WEATHER FOR CITY: $name")

            val weatherResponse = weatherService.getCurrentWeather(
                apiKey = BuildConfig.WEATHER_API_KEY,
                name = name
            )
            Log.d(TAG, "API RESPONSE: $weatherResponse")

            val domainWeather = weatherResponse.toDomain()
            Log.d(TAG, "MAPPED WEATHER: $domainWeather")
            emit(domainWeather)
        } catch (e: Exception) {
            Log.e(TAG, "ERROR FETCHING WEATHER FOR CITY: $name, ${e.message}")
            throw e
        }
    }

    companion object {
        private const val TAG = "WeatherRemoteDataSourceImpl"
    }
}
