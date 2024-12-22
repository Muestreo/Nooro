package com.kryptopass.nooro.feature.home

import com.kryptopass.nooro.core.domain.entity.Result
import com.kryptopass.nooro.core.domain.repository.CityDataStore
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.shared.common.state.UiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var cityDataStore: CityDataStore
    private lateinit var converter: HomeConverter
    private lateinit var usecase: FetchWeatherByCityUseCase
    private lateinit var SUT: HomeViewModel

    private val city = "London"
    private val error = "NETWORK ERROR: Failed to load weather data"

    @Before
    fun setup() {
        cityDataStore = mockk(relaxed = true)
        converter = mockk(relaxed = true)
        usecase = mockk(relaxed = true)

        SUT = HomeViewModel(
            cityDataStore = cityDataStore,
            converter = converter,
            usecase = usecase
        )
    }

    @Test
    fun `test initial state is UiState_Empty`() {
        assertEquals(UiState.Empty, SUT.uiStateFlow.value)
    }

    @Test
    fun `test loadPersistedCityWeather emits UiState_Empty for empty city`() = runTest {
        coEvery { cityDataStore.getCity() } returns flowOf("")

        SUT.loadPersistedCityWeather()

        assertEquals(UiState.Empty, SUT.uiStateFlow.value)
    }

    @Test
    fun `test loadPersistedCityWeather calls fetchWeather for valid city`() = runTest {
        coEvery { cityDataStore.getCity() } returns flowOf(city)
        coEvery { usecase.execute(any()) } returns flowOf(mockk())

        SUT.loadPersistedCityWeather()

        advanceUntilIdle()

        coVerify { usecase.execute(FetchWeatherByCityUseCase.Request(city)) }
    }

    @Test
    fun `test fetchWeather emits UiState_Loading and UiState_Success`() = runTest {
        val mockResponse = FetchWeatherByCityUseCase.Response(TestData.generateDomainWeatherModel(city))
        val mockModel = TestData.generateHomeModel(city)

        coEvery { usecase.execute(any()) } returns flowOf(Result.Success(mockResponse))
        every { converter.convert(Result.Success(mockResponse)) } returns UiState.Success(mockModel)

        SUT.fetchWeather(city)

        assertEquals(UiState.Empty, SUT.uiStateFlow.value)
        advanceUntilIdle()
        assertEquals(UiState.Success(mockModel), SUT.uiStateFlow.value)
    }

    @Test
    fun `test fetchWeather emits UiState_Error on failure`() = runTest {
        val exception = Exception(error)
        coEvery { usecase.execute(any()) } returns flow { throw exception }

        SUT.fetchWeather(city)

        advanceUntilIdle()

        val state = SUT.uiStateFlow.value

        assertTrue(state is UiState.Error)
        assertEquals(error, (state as UiState.Error).errorMessage)
    }
}
