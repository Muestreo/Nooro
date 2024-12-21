package com.kryptopass.nooro.core.domain.usecase

import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchWeatherByCityUseCase(
    configuration: Configuration,
    private val weatherRepository: WeatherRepository
) : UseCase<FetchWeatherByCityUseCase.Request, FetchWeatherByCityUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        weatherRepository.getCurrentWeather(request.name)
            .map {
                Response(it)
            }

    data class Request(val name: String) : UseCase.Request
    data class Response(val weather: Weather) : UseCase.Response
}