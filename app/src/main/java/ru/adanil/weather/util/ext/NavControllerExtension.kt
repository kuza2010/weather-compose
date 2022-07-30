package ru.adanil.weather.util.ext

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import ru.adanil.weather.ui.screens.WeatherScreens

fun NavController.navigateTo(screen: WeatherScreens) {
    this.navigate(screen.route)
}

fun NavController.navigateSingleTop(screen: WeatherScreens) {
    this.navigate(screen.route, NavOptions.Builder().setLaunchSingleTop(true).build())
}

fun NavController.navigateToTopActivity(top: WeatherScreens) {
    this.navigate(top.route) {
        launchSingleTop = true
        this@navigateToTopActivity.currentDestination
            ?.route
            ?.let { currentRoute ->
                popUpTo(currentRoute) {
                    inclusive = true
                }
            }
    }
}
