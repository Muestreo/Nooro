package com.kryptopass.nooro.core.network.datasource

import android.util.Log
import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.network.BuildConfig
import com.kryptopass.nooro.core.network.model.WeatherResponse
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

            val domainWeather = mapDomainWeather(weatherResponse)
            Log.d(TAG, "MAPPED WEATHER: $domainWeather")
            emit(domainWeather)
        } catch (e: Exception) {
            Log.e(TAG, "ERROR FETCHING WEATHER FOR CITY: $name, ${e.message}")
            throw e
        }
    }

    private fun mapDomainWeather(weatherResponse: WeatherResponse): Weather {
        Log.d(TAG, "MAPPED WEATHER ENTITY: $weatherResponse")
        return Weather(
            current = Current(
                condition = com.kryptopass.nooro.core.domain.entity.Condition(
                    code = weatherResponse.current?.condition?.code ?: 0,
                    icon = weatherResponse.current?.condition?.icon.orEmpty(),
                    text = weatherResponse.current?.condition?.text.orEmpty()
                ),
                feelslikeC = weatherResponse.current?.feelslikeC ?: 0.0,
                feelslikeF = weatherResponse.current?.feelslikeF ?: 0.0,
                humidity = weatherResponse.current?.humidity ?: 0,
                tempC = weatherResponse.current?.tempC ?: 0.0,
                tempF = weatherResponse.current?.tempF ?: 0.0,
                uv = weatherResponse.current?.uv ?: 0.0
            ),
            location = Location(
                country = weatherResponse.location?.country.orEmpty(),
                name = weatherResponse.location?.name.orEmpty(),
                region = weatherResponse.location?.region.orEmpty()
            )
        )
    }

    companion object {
        private const val TAG = "WeatherRemoteDataSourceImpl"
    }
}
