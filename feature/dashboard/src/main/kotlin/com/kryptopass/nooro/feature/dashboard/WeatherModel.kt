package com.kryptopass.nooro.feature.dashboard

data class WeatherModel(
    val condition: ConditionModel?,
    val feelsLikeC: Double?,
    val feelsLikeF: Double?,
    val humidity: Int?,
    val region: String?,
    val tempC: Double?,
    val tempF: Double?,
    val uv: Double?,
)

data class ConditionModel(
    val code: Int? = 0,
    val icon: String? = "",
    val text: String? = ""
)