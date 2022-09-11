package ru.adanil.weather.ui.screens.home

import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.navigation.WeatherScreens
import ru.adanil.weather.ui.components.WeatherBackdropScaffold
import ru.adanil.weather.ui.components.WeatherSwipeToRefresh
import ru.adanil.weather.util.ext.navigateSingleTop

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
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
        headerHeight = 40.dp,
        backLayerContent = {
            BackContent(
                weather = uiState.weather,
                loading = uiState.loading,
                isRefreshing = isRefreshing,
                onAddButtonClick = navigateToCitiesScreen,
                onRefreshRequested = viewModel::refreshWeather,
            )
        },
        frontLayerContent = {
            FrontContent(
                weather = uiState.weather,
                loading = uiState.loading,
                onAddButtonClick = navigateToCitiesScreen,
            )
        }
    )
}

@Composable
fun BackContent(
    loading: Boolean,
    isRefreshing: Boolean,
    weather: CurrentWeather?,
    onAddButtonClick: () -> Unit,
    onRefreshRequested: () -> Unit,
) {
    when {
        loading || weather == null -> {
            EmptyWeatherSummary(loading, onAddButtonClick)
        }
        else -> {
            WeatherSwipeToRefresh(
                isRefreshing = isRefreshing,
                onRefreshRequested = onRefreshRequested,
            ) {
                WeatherSummary(weather)
            }
        }
    }
}

@Composable
fun FrontContent(
    loading: Boolean,
    weather: CurrentWeather?,
    onAddButtonClick: () -> Unit,
) {
    when {
        loading || weather == null -> {
            EmptyWeatherSummary(
                loading = loading,
                onAddButtonClick = onAddButtonClick
            )
        }
        else -> {
            WeatherDetails(currentWeather = weather)
        }
    }
}
