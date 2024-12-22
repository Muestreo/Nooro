package com.kryptopass.nooro.feature.search

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

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var cityDataStore: CityDataStore
    private lateinit var converter: SearchConverter
    private lateinit var usecase: FetchWeatherByCityUseCase
    private lateinit var SUT: SearchViewModel

    private val city = "London"
    private val error = "NETWORK ERROR: Failed to load weather data"

    @Before
    fun setup() {
        cityDataStore = mockk(relaxed = true)
        converter = mockk(relaxed = true)
        usecase = mockk(relaxed = true)

        SUT = SearchViewModel(
            cityDataStore = cityDataStore,
            converter = converter,
            usecase = usecase
        )
    }

    @Test
    fun `test initial state is UiState_Loading`() {
        assertEquals(UiState.Loading, SUT.uiStateFlow.value)
    }

    @Test
    fun `test loadWeather emits UiState_Loading and UiState_Success`() = runTest {
        val mockResponse = FetchWeatherByCityUseCase.Response(TestData.generateDomainWeatherModel(city))
        val mockSearchModel = TestData.generateSearchModel(city)

        coEvery { usecase.execute(any()) } returns flowOf(Result.Success(mockResponse))
        every { converter.convert(Result.Success(mockResponse)) } returns UiState.Success(mockSearchModel)

        SUT.loadWeather(city)

        assertEquals(UiState.Loading, SUT.uiStateFlow.value)
        advanceUntilIdle()
        assertEquals(UiState.Success(mockSearchModel), SUT.uiStateFlow.value)
        assertTrue(SUT.searchedCities.value.contains(mockSearchModel))
    }

    @Test
    fun `test loadWeather emits UiState_Error on failure`() = runTest {
        val exception = Exception(error)

        coEvery { usecase.execute(any()) } returns flow { throw exception }

        SUT.loadWeather(city)

        advanceUntilIdle()
        val state = SUT.uiStateFlow.value
        assertTrue(state is UiState.Error)
        assertEquals(error, (state as UiState.Error).errorMessage)
    }

    @Test
    fun `test saveCity calls cityDataStore`() = runTest {
        SUT.saveCity(city)

        advanceUntilIdle()

        coVerify { cityDataStore.saveCity(city) }
    }
}
