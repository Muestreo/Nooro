package com.kryptopass.nooro.feature.home

import com.kryptopass.nooro.core.domain.entity.Weather

object TestData {

    fun generateHomeModel(city: String): HomeModel {
        return HomeModel(
            condition = ConditionModel(
                icon = "//cdn.weatherapi.com/weather/64x64/night/119.png"
            ),
            feelsLikeC = 3.2,
            feelsLikeF = 37.8,
            humidity = 86,
            name = city,
            tempC = 6.1,
            tempF = 43.1,
            uv = 1.0
        )
    }

    fun generateDomainWeatherModel(city: String): Weather {
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
                name = city,
                region = "City of London, Greater London"
            )
        )
    }
}
