package com.kryptopass.nooro.feature.search

import com.kryptopass.nooro.core.domain.entity.Weather

object TestData {

    fun generateSearchModel(city: String): SearchModel {
        return SearchModel(
            condition = ConditionModel(
                icon = "//cdn.weatherapi.com/weather/64x64/night/119.png"
            ),
            name = city,
            tempC = 6.1,
            tempF = 43.1
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
