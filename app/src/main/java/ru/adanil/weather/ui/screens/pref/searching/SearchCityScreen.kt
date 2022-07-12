package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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

    WeatherSearchWidget(
        searchQuery = searchQuery,
        placeholder = R.string.placeholder_city_search,
        onBackIconClick = { navController.popBackStack() },
        onSearchIconClick = {},
        onSearchQueryChange = { viewModel.updateSearchQuery(it) },
    )
}