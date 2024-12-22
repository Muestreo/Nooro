package com.kryptopass.nooro.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kryptopass.nooro.shared.common.state.CommonScreen

@Composable
fun SearchScreen(
    city: String,
    onCitySelected: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by searchViewModel.uiStateFlow.collectAsState()
    var initialCityProcessed by remember { mutableStateOf(false) }

    LaunchedEffect(city) {
        if (!initialCityProcessed && city.isNotEmpty()) {
            searchViewModel.saveCity(city)
            searchViewModel.loadWeather(city)
            initialCityProcessed = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(onSearch = { searchedCity ->
            searchViewModel.saveCity(searchedCity)
            searchViewModel.loadWeather(searchedCity)
        })

        CommonScreen(
            state = uiState,
            onRetry = {
                if (city.isNotEmpty()) {
                    searchViewModel.loadWeather(city)
                }
            }
        ) { model ->
            SearchContent(model)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (city.isNotEmpty()) {
                    searchViewModel.saveCity(city)
                }
                onCitySelected()
            }
        ) {
            Text("HOME SCREEN")
        }
    }
}