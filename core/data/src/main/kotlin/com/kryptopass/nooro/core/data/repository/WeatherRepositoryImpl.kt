package com.kryptopass.nooro.core.data.repository

import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.first

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        return remoteDataSource.getCurrentWeather(city).first()
    }
}