package com.kryptopass.nooro.core.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kryptopass.nooro.core.domain.repository.CityDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityDataStoreImpl @Inject constructor(
    private val context: Context
): CityDataStore {

    override suspend fun saveCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[CITY_KEY] = city
        }
    }

    override fun getCity(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[CITY_KEY]
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "settings")
        private val CITY_KEY = stringPreferencesKey("city")
    }
}