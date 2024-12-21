package com.kryptopass.nooro.di

import com.kryptopass.nooro.core.repository.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.repository.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import com.kryptopass.nooro.core.repository.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository = WeatherRepositoryImpl(
        localDataSource,
        remoteDataSource
    )
}