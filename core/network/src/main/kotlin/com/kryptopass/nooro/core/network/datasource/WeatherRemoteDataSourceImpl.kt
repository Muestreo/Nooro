package com.kryptopass.nooro.core.network.datasource

import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Condition
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

    override fun getCurrentWeather(city: String): Flow<Weather> = flow {
        try {
            val weatherResponse = weatherService.getCurrentWeather(
                apiKey = BuildConfig.WEATHER_API_KEY,
                city = city
            )
            emit(weatherResponse.toDomain())
        } catch (e: Exception) {
            throw e
        }
    }

    private fun WeatherResponse.toDomain(): Weather {
        return Weather(
            current = current?.toDomain(),
            location = location?.toDomain()
        )
    }

    private fun com.kryptopass.nooro.core.network.model.Condition.toDomain(): Condition {
        return Condition(
            code = code,
            icon = icon,
            text = text
        )
    }

    private fun com.kryptopass.nooro.core.network.model.Current.toDomain(): Current {
        return Current(
            condition = condition?.toDomain(),
            feelslikeC = feelslikeC,
            feelslikeF = feelslikeF,
            humidity = humidity,
            tempC = tempC,
            tempF = tempF,
            uv = uv
        )
    }

    private fun com.kryptopass.nooro.core.network.model.Location.toDomain(): Location {
        return Location(
            country = country,
            name = name,
            region = region
        )
    }
}