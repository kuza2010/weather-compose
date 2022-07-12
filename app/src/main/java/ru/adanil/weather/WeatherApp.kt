package ru.adanil.weather

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.HiltAndroidApp
import ru.adanil.weather.ui.components.WeatherSnackbar
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.screens.home.HomeScreen
import ru.adanil.weather.ui.screens.home.HomeScreenViewModel
import ru.adanil.weather.ui.screens.offline.OfflineScreen
import ru.adanil.weather.ui.screens.pref.CitiesBagScreen
import ru.adanil.weather.ui.screens.pref.searching.SearchCityScreen
import ru.adanil.weather.ui.screens.pref.UserCitiesViewModel
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
                    snackbar = { snackbarData ->
                        WeatherSnackbar(snackbarData = snackbarData)
                    }
                )
            },
            scaffoldState = appState.scaffoldState
        ) {
            NavHost(
                startDestination = startDestination,
                navController = appState.navController
            ) {
                weatherAppNavGraph(appState.navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.weatherAppNavGraph(navController: NavHostController) {
    composable(route = WeatherScreens.MainScreen.route) {
        val vm = hiltViewModel<HomeScreenViewModel>()
        HomeScreen(navController, vm)
    }
    composable(route = WeatherScreens.OfflineScreen.route) {
        OfflineScreen()
    }
    // make it nested
    composable(route = WeatherScreens.UserCitiesScreen.route) {
        val vm = hiltViewModel<UserCitiesViewModel>()
        CitiesBagScreen(navController, vm)
    }
    composable(route = WeatherScreens.SearchCitiesScreen.route) {
        SearchCityScreen(navController)
    }
}