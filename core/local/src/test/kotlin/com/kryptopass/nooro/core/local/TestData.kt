package com.kryptopass.nooro.core.local

import com.kryptopass.nooro.core.local.room.ConditionEntity
import com.kryptopass.nooro.core.local.room.WeatherEntity
import com.kryptopass.nooro.core.domain.entity.Weather

object TestData {

    fun generateWeatherEntity(): WeatherEntity {
        return WeatherEntity(
            name = "London",
            condition = ConditionEntity(
                code = 1006,
                icon = "//cdn.weatherapi.com/weather/64x64/night/119.png",
                text = "Cloudy"
            ),
            country = "United Kingdom",
            feelsLikeC = 3.2,
            feelsLikeF = 37.8,
            region = "City of London, Greater London",
            humidity = 86,
            tempC = 6.1,
            tempF = 43.1,
            uv = 1.0
        )
    }

    // NOTE: duplicate in network tests,
    // reason for testing patterns (i.e. Object Mother, Fixture, Fakes, etc.)
    fun generateDomainWeatherModel(name: String): Weather {
        return Weather(
            com.kryptopass.nooro.core.domain.entity.Current(
                condition = com.kryptopass.nooro.core.domain.entity.Condition(
                    code = 1006,
                    icon = "//cdn.weatherapi.com/weather/64x64/night/119.png",
                    text = "Cloudy"
                ),
                feelslikeC = 3.2,
                feelslikeF = 37.8,
                humidity = 86,
                tempC = 6.1,
                tempF = 43.1,
                uv = 1.0,
            ),
            com.kryptopass.nooro.core.domain.entity.Location(
                country = "United Kingdom",
                name = name,
                region = "City of London, Greater London"
            )
        )
    }
}