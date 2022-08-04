package ru.adanil.weather.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.adanil.weather.ui.screens.home.HomeScreen
import ru.adanil.weather.ui.screens.home.HomeScreenViewModel
import ru.adanil.weather.ui.screens.offline.OfflineScreen
import ru.adanil.weather.ui.screens.offline.OfflineScreenViewModel
import ru.adanil.weather.ui.screens.pref.CitiesBagScreen
import ru.adanil.weather.ui.screens.pref.UserCitiesViewModel
import ru.adanil.weather.ui.screens.pref.searching.SearchCityScreen

@Composable
fun WeatherNavHost(
    modifier: Modifier,
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        weatherAppNavGraph(navController)
    }
}

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalCoroutinesApi::class
)
private fun NavGraphBuilder.weatherAppNavGraph(navController: NavHostController) {
    composable(route = WeatherScreens.MainScreen.route) {
        val vm = hiltViewModel<HomeScreenViewModel>()
        HomeScreen(navController, vm)
    }
    composable(route = WeatherScreens.OfflineScreen.route) {
        val vm = hiltViewModel<OfflineScreenViewModel>()
        OfflineScreen(navController, vm)
    }
    citySettingsNavGraph(navController)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.citySettingsNavGraph(navController: NavHostController) {
    navigation(
        route = "city-settings",
        startDestination = WeatherScreens.UserCitiesScreen.route
    ) {
        composable(route = WeatherScreens.UserCitiesScreen.route) {
            val vm = hiltViewModel<UserCitiesViewModel>()
            CitiesBagScreen(navController, vm)
        }
        composable(route = WeatherScreens.SearchCitiesScreen.route) {
            SearchCityScreen(navController)
        }
    }
}
