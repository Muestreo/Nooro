package com.kryptopass.nooro.di

import com.kryptopass.nooro.core.repository.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.repository.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.local.datasource.WeatherLocalDataSourceImpl
import com.kryptopass.nooro.core.remote.datasource.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}
