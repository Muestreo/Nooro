package com.kryptopass.nooro.core.database.di

import android.content.Context
import androidx.room.Room
import com.kryptopass.nooro.core.database.room.WeatherDao
import com.kryptopass.nooro.core.database.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "weather_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao = database.weatherDao()
}