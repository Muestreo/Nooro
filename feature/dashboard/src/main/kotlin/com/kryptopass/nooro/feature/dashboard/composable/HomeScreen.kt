package com.kryptopass.nooro.feature.dashboard.composable

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kryptopass.nooro.feature.dashboard.WeatherViewModel
import com.kryptopass.nooro.shared.common.state.CommonScreen

@Composable
fun HomeScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    onNavigateToSearch: (String) -> Unit
) {
    val uiState by weatherViewModel.uiStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        weatherViewModel.loadPersistedCityWeather()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(onSearch = { city ->
            onNavigateToSearch(city)
        })

        CommonScreen(
            state = uiState,
            onRetry = { weatherViewModel.loadPersistedCityWeather() }
        ) { model ->
            WeatherContent(model)
        }
    }
}
