package com.kryptopass.nooro.feature.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kryptopass.nooro.core.domain.services.NoOpLogger
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.core.domain.usecase.UseCase
import com.kryptopass.nooro.feature.home.HomeUiAction
import com.kryptopass.nooro.feature.home.HomeUiSingleEvent
import com.kryptopass.nooro.feature.home.HomeViewModel
import com.kryptopass.nooro.feature.home.mock.FakeHomeConverter
import com.kryptopass.nooro.feature.home.mock.MockCityDataStore
import com.kryptopass.nooro.feature.home.mock.MockWeatherRepository
import com.kryptopass.nooro.shared.common.DefaultDispatcherProvider
import com.kryptopass.nooro.shared.common.state.CommonScreen
import com.kryptopass.nooro.shared.common.theme.NooroTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToSearch: (String) -> Unit
) {
    val uiState by homeViewModel.uiStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.loadPersistedCityWeather()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HomeBar(onSearch = { city ->
            homeViewModel.submitAction(
                HomeUiAction.OnSearchBarEnterDoneItemClick(
                    city
                )
            )
        })

        CommonScreen(
            state = uiState,
            onRetry = { homeViewModel.loadPersistedCityWeather() }
        ) { model ->
            HomeContent(model)
        }
    }

    LaunchedEffect(Unit) {
        homeViewModel.singleEventFlow.collectLatest { event ->
            when (event) {
                is HomeUiSingleEvent.OpenSearchScreen -> {
                    onNavigateToSearch(event.city)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val mockUseCase = FetchWeatherByCityUseCase(
        configuration = UseCase.Configuration(
            dispatcher = DefaultDispatcherProvider(),
            logger = NoOpLogger()
        ),
        weatherRepository = MockWeatherRepository()
    )

    val homeViewModel = HomeViewModel(MockCityDataStore(), FakeHomeConverter(), mockUseCase)

    NooroTheme {
        HomeScreen(
            homeViewModel,
            onNavigateToSearch = {}
        )
    }
}
