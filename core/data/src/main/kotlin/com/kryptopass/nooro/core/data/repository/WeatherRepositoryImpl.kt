package com.kryptopass.nooro.core.data.repository

import android.util.Log
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

    override fun getCurrentWeather(name: String): Flow<Weather> = flow {
        try {
            Log.d(TAG, "FETCH WEATHER FOR CITY: $name")

            val localWeather = localDataSource.getCurrentWeather(name).firstOrNull()
            if (localWeather != null) {
                Log.d(TAG, "LOCAL WEATHER: $localWeather")
                emit(localWeather)
            } else {
                Log.d(TAG, "LOCAL WEATHER NOT FOUND. FETCH FROM REMOTE.")
                val remoteWeather = remoteDataSource.getCurrentWeather(name).first()
                Log.d(TAG, "REMOTE WEATHER: $remoteWeather")

                localDataSource.addCurrentWeather(remoteWeather)
                Log.d(TAG, "REMOTE ADDED TO LOCAL.")
                emit(remoteWeather)
            }
        } catch (e: Exception) {
            Log.e(TAG, "ERROR FETCHING WEATHER: ${e.message}", e)
            throw e
        }
    }

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}