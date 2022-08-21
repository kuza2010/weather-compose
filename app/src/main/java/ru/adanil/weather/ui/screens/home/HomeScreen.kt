package ru.adanil.weather.ui.screens.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.navigation.WeatherScreens
import ru.adanil.weather.ui.components.SecondaryButton
import ru.adanil.weather.ui.components.WeatherBackdropScaffold
import ru.adanil.weather.ui.theme.WeatherTheme
import ru.adanil.weather.util.ext.navigateSingleTop

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    exampleViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by exampleViewModel.uiState.collectAsState()
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val scrollState = rememberScrollState()

    val navigateToCitiesScreen = remember(navController) {
        { navController.navigateSingleTop(WeatherScreens.UserCitiesScreen) }
    }

    WeatherBackdropScaffold(
        appBar = {
            HomeAppBar(
                currentCity = uiState.city,
                onAppSettingsClick = {},
                onChangeLocationClick = { navigateToCitiesScreen() },
                connectionStatus = uiState.connectionStatus
            )
        },
        scaffoldState = backdropState,
        backLayerContent = {
            MainContent(
                weather = uiState.weather,
                scrollState = scrollState,
                onAddButtonClick = navigateToCitiesScreen
            )
        },
        frontLayerContent = {
            ExtraContent(
                scrollState = scrollState
            )
        }
    )
}

@Composable
fun MainContent(
    weather: CurrentWeather?,
    scrollState: ScrollState,
    onAddButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (weather == null) {
            Text(
                style = WeatherTheme.typography.h6,
                text = stringResource(R.string.message_select_city_hint)
            )
            SecondaryButton(onAddButtonClick) {
                Text(text = stringResource(R.string.message_select_city_variant))
            }
        }
    }
}

@Composable
fun ExtraContent(
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp, vertical = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "frontLayerContent")
    }
}
