package com.kryptopass.nooro.core.data.repository

import com.kryptopass.nooro.core.data.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getWeather(city: String): Flow<Weather> = flow {
        val localWeather = localDataSource.getCurrentWeather(city).firstOrNull()

        if (localWeather != null) {
            emit(localWeather)
        } else {
            val remoteWeather = remoteDataSource.getCurrentWeather(city).first()
            localDataSource.addCurrentWeather(remoteWeather)
            emit(remoteWeather)
        }
    }
}