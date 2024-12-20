package com.kryptopass.nooro.feature.dashboard

data class WeatherModel(
    val region: String?,
    val tempF: Double?,
    val tempC: Double?,
    val condition: ConditionModel?,
    val humidity: Int?,
    val uv: Double?,
    val feelsLikeF: Double?,
    val feelsLikeC: Double?
)

data class ConditionModel(
    val code: Int? = 0,
    val icon: String? = "",
    val text: String? = ""
)