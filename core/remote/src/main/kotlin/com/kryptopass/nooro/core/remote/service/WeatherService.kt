package com.kryptopass.nooro.core.remote.service

import com.kryptopass.nooro.core.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") name: String
    ): WeatherResponse
}
