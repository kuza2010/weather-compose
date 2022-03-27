package ru.adanil.weather.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.screens.offline.OfflineActivity

@Composable
fun WeatherNavigation(
    navController: NavHostController,
    startDestination: String = WeatherScreens.MainScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = WeatherScreens.MainScreen.route) {
            Text(text = "You are on main screen")
        }
        composable(route = WeatherScreens.OfflineScreen.route) {
            OfflineActivity()
        }
    }
}