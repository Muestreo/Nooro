package com.kryptopass.nooro.core.domain.repository

import com.kryptopass.nooro.core.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
}