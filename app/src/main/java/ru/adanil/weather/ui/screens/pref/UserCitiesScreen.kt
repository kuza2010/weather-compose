package ru.adanil.weather.ui.screens.pref

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.model.City
import ru.adanil.weather.ui.components.WeatherCard
import ru.adanil.weather.ui.components.WeatherIconMedium
import ru.adanil.weather.ui.components.WeatherTopAppBar
import ru.adanil.weather.ui.theme.WeatherTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCitiesScreen(
    navController: NavController,
    viewModel: UserCitiesViewModel,
) {
    val uiState: UserCitiesUiState by viewModel.uiState.collectAsState()
    val dismissState = rememberDismissState()

    Column() {
        WeatherTopAppBar() {
            IconButton(onClick = { navController.popBackStack() }) {
                WeatherIconMedium(
                    contentDescription = "Go back",
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack)
                )
            }
            Text(text = stringResource(id = R.string.title_user_cities))
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.cities, { it.name }) { city ->
                CityCard(city = city)
            }
        }
    }
}

@Composable
fun CityCard(city: City) {
    WeatherCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, end = 12.dp),
        onClick = { /* do nothing */ }
    ) {
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(12.dp),
            style = WeatherTheme.typography.h4,
            text = city.name.replaceFirstChar { it.uppercase() }
        )
    }
}