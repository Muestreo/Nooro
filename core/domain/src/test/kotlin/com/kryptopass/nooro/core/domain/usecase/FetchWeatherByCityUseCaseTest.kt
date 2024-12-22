package com.kryptopass.nooro.core.domain.usecase

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FetchWeatherByCityUseCaseTest {
    private val weatherRepository = mock<WeatherRepository>()
    private val useCase = FetchWeatherByCityUseCase(
        mock(),
        weatherRepository
    )

    @Test
    fun testProcess() = runTest {
        val request = FetchWeatherByCityUseCase.Request("Bellevue")
        val weather = Weather(
            current = Current(
                condition = Condition(),
                tempC = 0.0,
                tempF = 32.0,
            ),
            location = Location(
                country = "US",
                name = "Bellevue",
            )
        )

        whenever(weatherRepository.getCurrentWeather(request.name)).thenReturn(flowOf(weather))
        val response = useCase.process(request).first()

        assertEquals(FetchWeatherByCityUseCase.Response(weather), response)
    }
}
