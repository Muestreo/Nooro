package com.kryptopass.nooro.di

import com.kryptopass.nooro.AndroidLogger
import com.kryptopass.nooro.core.domain.services.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoggingModule {

    @Provides
    fun provideLogger(): Logger = AndroidLogger()
}
