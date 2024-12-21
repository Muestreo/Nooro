package com.kryptopass.nooro.core.network.datasource

import com.kryptopass.nooro.core.domain.entity.UseCaseException
import com.kryptopass.nooro.core.network.BuildConfig
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
    private val SUT = WeatherRemoteDataSourceImpl(service)

    private val apiKey = BuildConfig.WEATHER_API_KEY
    private val name = "London"

    @Test
    fun testGetCurrentWeather() = runTest {
        val remoteWeatherResponse = TestData.generateWeatherResponse(name)
        val expectedWeather = TestData.generateDomainWeatherModel(name)

        whenever(service.getCurrentWeather(apiKey, name)).thenReturn(remoteWeatherResponse)
        val result = SUT.getCurrentWeather(name).first()

        assertEquals(expectedWeather.current?.condition?.icon, result.current?.condition?.icon)
        assertEquals(expectedWeather.current?.condition?.text, result.current?.condition?.text)
        assertEquals(expectedWeather.current?.feelslikeC, result.current?.feelslikeC)
        assertEquals(expectedWeather.current?.humidity, result.current?.humidity)
        assertEquals(expectedWeather.current?.tempF, result.current?.tempF)
        assertEquals(expectedWeather.location?.name, result.location?.name)
        assertEquals(expectedWeather.location?.region, result.location?.region)
    }

    @Test
    fun testGetCurrentWeatherThrowsError() = runTest {
        whenever(service.getCurrentWeather(apiKey, name)).thenThrow(RuntimeException())

        SUT.getCurrentWeather(name).catch {
            assertTrue(it is UseCaseException.WeatherException)
        }
    }
}
