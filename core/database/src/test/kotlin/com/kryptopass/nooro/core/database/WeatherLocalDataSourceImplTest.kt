package com.kryptopass.nooro.core.database

import com.kryptopass.nooro.core.database.datasource.WeatherLocalDataSourceImpl
import com.kryptopass.nooro.core.database.room.WeatherDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class WeatherLocalDataSourceImplTest {

    private val dao = mock<WeatherDao>()
    private val SUT = WeatherLocalDataSourceImpl(dao)

    private val name = "London"

    @Test
    fun testGetCurrentWeather() = runTest {
        val weatherEntity = TestData.generateWeatherEntity()
        val expectedWeather = TestData.generateDomainWeatherModel(name)

        whenever(dao.getWeatherByCity(name)).thenReturn(flowOf(weatherEntity))
        val result = SUT.getCurrentWeather(name).first()

        assertEquals(expectedWeather.current?.feelslikeF, result?.current?.feelslikeF)
        assertEquals(expectedWeather.current?.humidity, result?.current?.humidity)
        assertEquals(expectedWeather.current?.tempC, result?.current?.tempC)
        assertEquals(expectedWeather.current?.uv, result?.current?.uv)
        assertEquals(expectedWeather.location?.country, result?.location?.country)
        assertEquals(expectedWeather.location?.name, result?.location?.name)
    }

    @Test
    fun testAddWeather() = runTest {
        val weatherEntity = TestData.generateWeatherEntity()
        val expectedWeather = TestData.generateDomainWeatherModel(name)

        SUT.addWeather(expectedWeather)

        verify(dao).insertWeather(weatherEntity)
    }
}
