package com.kryptopass.nooro.feature.dashboard.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kryptopass.nooro.feature.dashboard.state.WeatherUiState
import com.kryptopass.nooro.feature.dashboard.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(onSearch = { name ->
            viewModel.fetchWeather(name)
        })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (uiState) {
                is WeatherUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is WeatherUiState.Empty -> {
                    EmptyStateContent()
                }
                is WeatherUiState.Success -> {
                    WeatherContent(weather = (uiState as WeatherUiState.Success).weather)
                }
                is WeatherUiState.Error -> {
                    ErrorScreen(
                        message = (uiState as WeatherUiState.Error).message,
                        onRetry = { viewModel.fetchWeather("London") }
                    )
                }
            }
        }
    }
}
