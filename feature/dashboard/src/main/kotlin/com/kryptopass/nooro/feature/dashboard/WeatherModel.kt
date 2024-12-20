package com.kryptopass.nooro.feature.dashboard

data class WeatherModel(
    val city: String,
    val tempF: String,
    val condition: String,
    val humidity: String,
    val uv: String,
    val feelsLikeF: String
)