package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.adanil.weather.R
import ru.adanil.weather.ui.components.ConnectivityStatus
import ru.adanil.weather.ui.components.WeatherSearchWidget
import ru.adanil.weather.ui.theme.WeatherTheme

@ExperimentalCoroutinesApi
@Composable
fun SearchCityScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        ConnectivityStatus()
        WeatherSearchWidget(
            searchQuery = uiState.searchQuery,
            onSearchIconClick = { viewModel.searchCity() },
            onBackIconClick = { navController.popBackStack() },
            placeholder = R.string.search_placeholder_city_search,
            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.citiesThatMatchCriteria, { it.id }) { city ->
                SearchCityResult(
                    city = city,
                    onCityClick = {
                        viewModel.selectCity(city)
                        navController.popBackStack()
                    }
                )
            }

            if (uiState.hasResult.not()) item { EmptyResult() }
        }
    }
}

@Composable
fun EmptyResult() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, end = 12.dp),
        textAlign = TextAlign.Center,
        style = WeatherTheme.typography.h5,
        text = stringResource(id = R.string.search_no_result_emoji)
    )
}
