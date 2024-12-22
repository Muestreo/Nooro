package com.kryptopass.nooro.di

import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import com.kryptopass.nooro.core.domain.services.DispatcherProvider
import com.kryptopass.nooro.core.domain.services.Logger
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.core.domain.usecase.UseCase
import com.kryptopass.nooro.shared.common.DefaultDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    fun provideUseCaseConfiguration(
        dispatcherProvider: DispatcherProvider,
        logger: Logger
    ): UseCase.Configuration = UseCase.Configuration(dispatcherProvider, logger)

    @Provides
    fun provideFetchWeatherByCityUseCase(
        configuration: UseCase.Configuration,
        repository: WeatherRepository
    ): FetchWeatherByCityUseCase = FetchWeatherByCityUseCase(
        configuration,
        repository
    )
}
