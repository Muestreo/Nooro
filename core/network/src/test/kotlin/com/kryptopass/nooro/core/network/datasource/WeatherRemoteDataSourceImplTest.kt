package com.kryptopass.nooro.core.network.datasource

import com.kryptopass.nooro.core.domain.entity.UseCaseException
import com.kryptopass.nooro.core.domain.entity.Weather
import com.kryptopass.nooro.core.network.model.Current
import com.kryptopass.nooro.core.network.model.Location
import com.kryptopass.nooro.core.network.model.WeatherResponse
import com.kryptopass.nooro.core.network.service.WeatherService
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class WeatherRemoteDataSourceImplTest {

    private val service = mock<WeatherService>()
    private val dataSource = WeatherRemoteDataSourceImpl(service)

    private val apiKey = "1234567"
    private val city = "London"

    @Test
    fun testGetCurrentWeather() = runTest {
        val remoteWeatherResponse =
            WeatherResponse(
                Current(
                    cloud = 0,
                    condition = null,
                    dewpointC = 0.0,
                    dewpointF = 0.0,
                    feelslikeC = 0.0,
                    feelslikeF = 0.0,
                    gustKph = 0.0,
                    gustMph = 0.0,
                    heatindexC = 0.0,
                    heatindexF = 0.0,
                    humidity = 0,
                    isDay = 0,
                    lastUpdated = "2021-09-01 12:00"
                ),
                Location(
                    country = "UK",
                    lat = 51.51,
                    lon = -0.13,
                    name = "London",
                    region = city
                )
            )

        val expectedWeather =
            Weather(
                com.kryptopass.nooro.core.domain.entity.Current(
                    condition = null,
                    feelslikeC = 0.0,
                    feelslikeF = 0.0,
                    humidity = 0
                ),
                com.kryptopass.nooro.core.domain.entity.Location(
                    country = "UK",
                    name = "London",
                    region = city
                )
            )

        whenever(service.getCurrentWeather(apiKey, city)).thenReturn(remoteWeatherResponse)
        val result = dataSource.getCurrentWeather(city).first()

        assertEquals(expectedWeather, result)
    }

    @Test
    fun testGetLaunchesThrowsError() = runTest {
        whenever(service.getCurrentWeather(apiKey, city)).thenThrow(RuntimeException())

        dataSource.getCurrentWeather(city).catch {
            assertTrue(it is UseCaseException.WeatherException)
        }
    }
}
