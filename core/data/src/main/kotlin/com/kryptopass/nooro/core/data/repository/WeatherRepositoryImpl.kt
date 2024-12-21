package com.kryptopass.nooro.core.data.repository

import android.util.Log
import com.kryptopass.nooro.core.data.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.data.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getCurrentWeather(name: String): Flow<Weather> = flow {
        Log.d(TAG, "FETCH WEATHER FOR CITY: $name")
        val localWeather = withContext(Dispatchers.IO) {
            localDataSource.getCurrentWeather(name).firstOrNull()
        }

        if (localWeather != null) {
            Log.d(TAG, "LOCAL WEATHER: $localWeather")
            emit(localWeather)
        } else {
            Log.d(TAG, "LOCAL WEATHER NOT FOUND. FETCH FROM REMOTE.")
            val remoteWeather = remoteDataSource.getCurrentWeather(name).first()
            Log.d(TAG, "REMOTE WEATHER: $remoteWeather")

            withContext(Dispatchers.IO) {
                localDataSource.addWeather(remoteWeather)
            }
            Log.d(TAG, "REMOTE ADDED TO LOCAL.")

            emit(remoteWeather)
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}