package com.kryptopass.nooro.feature.search

data class SearchModel(
    val condition: ConditionModel?,
    val name: String,
    val tempC: Double?,
    val tempF: Double?,
)

data class ConditionModel(
    val icon: String? = ""
)
