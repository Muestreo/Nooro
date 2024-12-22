package com.kryptopass.nooro.feature.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.kryptopass.nooro.feature.search.SearchUiAction
import com.kryptopass.nooro.feature.search.SearchUiSingleEvent
import com.kryptopass.nooro.feature.search.SearchViewModel
import com.kryptopass.nooro.shared.common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    city: String,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onCitySelected: (String) -> Unit
) {
    var initialCityProcessed by remember { mutableStateOf(false) }
    val uiState by searchViewModel.uiStateFlow.collectAsState()
    val searchedCities by searchViewModel.searchedCities.collectAsState()

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
        SearchBar(
            onSearch = { searchedCity ->
                searchViewModel.submitAction(
                    SearchUiAction.OnSearchBarEnterDoneItemClick(
                        searchedCity
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CommonScreen(
            state = uiState,
            onRetry = {
                if (city.isNotEmpty()) {
                    searchViewModel.loadWeather(city)
                }
            }
        ) {
            LazyColumn {
                items(searchedCities) { searchModel ->
                    SearchCityCard(searchModel) {
                        searchViewModel.saveCity(searchModel.name ?: "")
                        searchViewModel.submitAction(
                            SearchUiAction.OnCityWeatherCardItemClick(searchModel)
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        searchViewModel.singleEventFlow.collectLatest { event ->
            when (event) {
                is SearchUiSingleEvent.BackToHomeScreen -> {
                    searchViewModel.saveCity(event.city)
                    onCitySelected(event.city)
                }
                is SearchUiSingleEvent.AddCityToCityList -> {
                    searchViewModel.saveCity(event.city)
                    searchViewModel.loadWeather(event.city)
                }
            }
        }
    }
}
