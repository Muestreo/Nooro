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
import com.kryptopass.nooro.feature.home.HomeUiAction
import com.kryptopass.nooro.feature.home.HomeUiSingleEvent
import com.kryptopass.nooro.feature.home.HomeViewModel
import com.kryptopass.nooro.shared.common.state.CommonScreen
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
    HomeScreen(
        hiltViewModel(),
        onNavigateToSearch = {}
    )
}
