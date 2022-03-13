package ru.adanil.weather.ui.screens

sealed class WeatherScreens(val route: String) {
    object MainScreen: WeatherScreens("main_screen")
    object OfflineScreen: WeatherScreens("offline_screen")
}