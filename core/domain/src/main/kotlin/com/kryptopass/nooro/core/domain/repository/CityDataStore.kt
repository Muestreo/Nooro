package com.kryptopass.nooro.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface CityDataStore {
    suspend fun saveCity(city: String)
    fun getCity(): Flow<String?>
}
