package com.kryptopass.nooro.core.domain.entity

data class Current(
    val condition: Condition? = Condition(),
    val feelslikeC: Double? = 0.0,
    val feelslikeF: Double? = 0.0,
    val humidity: Int? = 0,
    val region: String? = "",
    val tempC: Double? = 0.0,
    val tempF: Double? = 0.0,
    val uv: Double? = 0.0
)
