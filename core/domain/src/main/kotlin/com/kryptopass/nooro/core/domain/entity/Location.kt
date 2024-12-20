package com.kryptopass.nooro.core.domain.entity

data class Location(
    val country: String? = "",
    val lat: Double? = 0.0,
    val localtime: String? = "",
    val localtimeEpoch: Int? = 0,
    val lon: Double? = 0.0,
    val name: String? = "",
    val region: String? = "",
    val tzId: String? = ""
)