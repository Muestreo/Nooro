package com.kryptopass.nooro.core.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: Current? = Current(),
    @SerializedName("location")
    val location: Location? = Location()
)
