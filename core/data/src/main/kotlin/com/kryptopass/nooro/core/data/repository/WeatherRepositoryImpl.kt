package com.kryptopass.nooro.core.data.repository

import com.kryptopass.nooro.core.data.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getWeather(city: String): Flow<Weather> {
        TODO("Not yet implemented")
    }
}