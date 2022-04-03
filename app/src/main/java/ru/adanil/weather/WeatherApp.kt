package ru.adanil.weather

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.HiltAndroidApp
import ru.adanil.weather.ui.components.WeatherSnackbar
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.screens.main.HomeScreen
import ru.adanil.weather.ui.screens.offline.OfflineScreen
import ru.adanil.weather.ui.theme.WeatherTheme

@HiltAndroidApp
class App : Application()

@Composable
fun WeatherApp(
    startDestination: String = WeatherScreens.MainScreen.route
) {
    WeatherTheme {
        val appState = rememberWeatherAppState()
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    snackbar = { snackbarData -> WeatherSnackbar(snackbarData = snackbarData) }
                )
            },
            scaffoldState = appState.scaffoldState
        ) {
            NavHost(
                startDestination = startDestination,
                navController = appState.navController
            ) {
                weatherAppNavGraph()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.weatherAppNavGraph() {
    composable(route = WeatherScreens.MainScreen.route) {
        HomeScreen()
    }
    composable(route = WeatherScreens.OfflineScreen.route) {
        OfflineScreen()
    }
}