package ru.adanil.weather.util.ext

import androidx.navigation.NavController
import ru.adanil.weather.ui.screens.WeatherScreens

fun NavController.navigateTo(screen: WeatherScreens) {
    this.navigate(screen.route)
}