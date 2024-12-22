package com.kryptopass.nooro.shared.common.state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.kotlin.mock

@CommonModuleTests
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class BaseMVIViewModelTests
class MviViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>

    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = object : MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>() {
            override fun initState(): UiState<String> = UiState.Loading

            override fun handleAction(action: UiAction) {
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        //testDispatcher.cleanupTestCoroutines()
    }

    @Ignore("TODO: mock interface")
    fun testSubmitAction() = runTest {
        val uiAction = mock<UiAction>()
        viewModel = object : MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>() {
            override fun initState(): UiState<String> = UiState.Loading

            override fun handleAction(action: UiAction) {
                assertEquals(uiAction, action)
            }
        }
        viewModel.submitAction(mock<UiAction>())
    }

    @Test
    fun testSubmitState() = runTest {
        val uiState = UiState.Success("test")
        viewModel.submitState(uiState)
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }

    @Test
    fun testSubmitSingleEvent() = runTest {
        val uiSingleEvent = mock<UiSingleEvent>()
        viewModel.submitSingleEvent(uiSingleEvent)
        assertEquals(uiSingleEvent, viewModel.singleEventFlow.first())
    }
}
