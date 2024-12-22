package com.kryptopass.nooro.core.local.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.kryptopass.nooro.core.domain.repository.CityDataStore
import com.kryptopass.nooro.core.local.datasource.CityDataStoreImpl
import com.kryptopass.nooro.core.local.room.WeatherDao
import com.kryptopass.nooro.core.local.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideCityDataStore(
        @ApplicationContext context: Context
    ): CityDataStore = CityDataStoreImpl(context)

    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "weather_db"
        )
            .setQueryCallback(
                { sqlQuery, bindArgs ->
                    Log.d(TAG, "QUERY: $sqlQuery, ARGS: $bindArgs")
                },
                Executors.newSingleThreadExecutor()
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao = database.weatherDao()

    private const val TAG = "DatabaseModule"
}
