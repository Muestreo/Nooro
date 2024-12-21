package com.kryptopass.nooro.core.remote.datasource

import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.remote.model.Current
import com.kryptopass.nooro.core.remote.model.Location
import com.kryptopass.nooro.core.remote.model.Condition
import com.kryptopass.nooro.core.remote.model.WeatherResponse

object TestData {

    fun generateWeatherResponse(name: String): WeatherResponse {
        return WeatherResponse(
            Current(
                condition = Condition(
                    code = 1006,
                    icon = "//cdn.weatherapi.com/weather/64x64/night/119.png",
                    text = "Cloudy"
                ),
                feelslikeC = 3.2,
                feelslikeF = 37.8,
                humidity = 86,
                tempC = 6.1,
                tempF = 43.1,
                uv = 0.0
            ),
            Location(
                country = "United Kingdom",
                name = name,
                region = "City of London, Greater London"
            )
        )
    }

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
                uv = 0.0,
            ),
            com.kryptopass.nooro.core.domain.entity.Location(
                country = "United Kingdom",
                name = name,
                region = "City of London, Greater London"
            )
        )
    }
}