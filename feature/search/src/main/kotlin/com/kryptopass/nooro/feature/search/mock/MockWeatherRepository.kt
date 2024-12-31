package com.kryptopass.nooro.feature.search.mock

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockWeatherRepository : WeatherRepository {
    override fun getCurrentWeather(name: String): Flow<Weather> {
        return flowOf(
            Weather(
                location = Location(name = name),
                current = Current(
                    tempC = 20.0,
                    tempF = 68.0,
                    condition = Condition(icon = "Mock Icon")
                )
            )
        )
    }
}
