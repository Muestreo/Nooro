package com.kryptopass.nooro.core.repository

import DataModuleTests
import com.kryptopass.nooro.core.repository.datasource.WeatherLocalDataSource
import com.kryptopass.nooro.core.repository.datasource.WeatherRemoteDataSource
import com.kryptopass.nooro.core.repository.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.never
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@DataModuleTests
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class WeatherRepositoryTests
class WeatherRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    private val remoteDataSource = mock<WeatherRemoteDataSource>()
    private val localDataSource = mock<WeatherLocalDataSource>()
    private val SUT = WeatherRepositoryImpl(localDataSource, remoteDataSource)

    private val name = "London"

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCurrentWeather emits local weather when available`() = runTest {
        val weather = TestData.generateDomainWeatherModel("London")

        whenever(localDataSource.getCurrentWeather("London")).thenReturn(flowOf(weather))

        val result = SUT.getCurrentWeather("London").first()

        assertEquals(weather, result)
        verify(localDataSource, never()).addWeather(any())
    }
}
