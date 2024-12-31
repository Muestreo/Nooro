package com.kryptopass.nooro.feature.search.mock

import com.kryptopass.nooro.core.domain.repository.CityDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockCityDataStore : CityDataStore {
    override suspend fun saveCity(city: String) {
        // No-op for preview
    }

    override fun getCity(): Flow<String?> = flowOf("Mock City")
}
