package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.ui.components.WeatherSearchWidget

@Composable
fun SearchCityScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery
    val uiState: SearchUiState by viewModel.uiState.collectAsState()

    Column() {
        WeatherSearchWidget(
            searchQuery = searchQuery,
            placeholder = R.string.placeholder_city_search,
            onSearchIconClick = { viewModel.searchCity() },
            onBackIconClick = { navController.popBackStack() },
            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.citiesThatMatchCriteria, { "${it.latitude}-${it.longitude}" }) { city ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, top = 12.dp, end = 12.dp)
                ) {
                    Text(text = city.name)
                }
            }
        }
    }
}