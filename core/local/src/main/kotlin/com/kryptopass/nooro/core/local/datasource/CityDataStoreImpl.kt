package com.kryptopass.nooro.core.local.datasource

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kryptopass.nooro.core.domain.repository.CityDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityDataStoreImpl @Inject constructor(
    private val context: Context
): CityDataStore {

    override suspend fun saveCity(city: String) {
        Log.d(TAG, "SAVE CITY: $city")
        try {
            context.dataStore.edit { preferences ->
                preferences[CITY_KEY] = city
            }
            Log.d(TAG, "CITY SAVED: $city")
        } catch (e: Exception) {
            Log.e(TAG, "ERROR SAVING CITY: $city", e)
        }
    }

    override fun getCity(): Flow<String?> {
        Log.d(TAG, "FETCH CITY FROM DATASTORE")
        return context.dataStore.data.map { preferences ->
            val city = preferences[CITY_KEY]
            Log.d(TAG, "CITY RETRIEVED: $city")
            city
        }.catch { e ->
            Log.e(TAG, "ERROR FETCHING CITY: ${e.message}")
            emit(null)
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "settings")
        private val CITY_KEY = stringPreferencesKey("city")
        private val TAG = CityDataStoreImpl::class.java.simpleName
    }
}
