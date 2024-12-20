package com.kryptopass.nooro.core.domain.entity

data class Weather(
    val current: Current? = Current(),
    val location: Location? = Location()
)