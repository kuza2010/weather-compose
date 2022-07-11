package ru.adanil.weather.ui.screens.pref

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.ui.components.WeatherSearchWidget

@Composable
fun SearchCityScreen(
    navController: NavController,
) {
    WeatherSearchWidget(
        searchQuery = "",
        placeholder = R.string.placeholder_city_search,
        onBackIconClick = { navController.popBackStack() },
        onSearchIconClick = {},
        onSearchQueryChange = {},
    )
}