package com.kryptopass.nooro.di

import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.core.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideFetchWeatherByCityUseCase(
        configuration: UseCase.Configuration,
        repository: WeatherRepository
    ): FetchWeatherByCityUseCase = FetchWeatherByCityUseCase(
        configuration,
        repository
    )
}