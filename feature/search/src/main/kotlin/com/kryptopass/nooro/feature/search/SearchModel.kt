package com.kryptopass.nooro.feature.search

data class SearchModel(
    val condition: ConditionModel?,
    val feelsLikeC: Double?,
    val feelsLikeF: Double?,
    val humidity: Int?,
    val name: String?,
    val tempC: Double?,
    val tempF: Double?,
    val uv: Double?,
)

data class ConditionModel(
    val icon: String? = ""
)