package com.kryptopass.nooro.core.network.service

import com.kryptopass.nooro.core.network.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
        // @Query("aqi") aqi: String -> optional air quality index
    ): WeatherResponse
}