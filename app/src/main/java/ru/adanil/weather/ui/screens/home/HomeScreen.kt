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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.navigation.WeatherScreens
import ru.adanil.weather.ui.components.WeatherBackdropScaffold
import ru.adanil.weather.util.ext.navigateSingleTop

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    val navigateToCitiesScreen = remember(navController) {
        { navController.navigateSingleTop(WeatherScreens.UserCitiesScreen) }
    }

    WeatherBackdropScaffold(
        appBar = {
            HomeAppBar(
                currentCity = uiState.city,
                onAppSettingsClick = {},
                onChangeLocationClick = navigateToCitiesScreen,
                connectionStatus = uiState.connectionStatus
            )
        },
        scaffoldState = backdropState,
        backLayerContent = {
            BackContent(
                weather = uiState.weather,
                loading = uiState.loading,
                onAddButtonClick = navigateToCitiesScreen
            )
        },
        frontLayerContent = {
            FrontContent(
                scrollState = scrollState
            )
        }
    )
}

@Composable
fun BackContent(
    loading: Boolean,
    weather: CurrentWeather?,
    onAddButtonClick: () -> Unit
) {
    when {
        loading || weather == null -> EmptyWeatherSummary(loading, onAddButtonClick)
        else -> WeatherSummary(weather)
    }
}

@Composable
fun FrontContent(
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
