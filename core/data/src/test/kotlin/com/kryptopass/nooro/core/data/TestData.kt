package com.kryptopass.nooro.core.data

import com.kryptopass.nooro.core.domain.entity.Weather

object TestData {

    // NOTE: duplicate in database & network tests,
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