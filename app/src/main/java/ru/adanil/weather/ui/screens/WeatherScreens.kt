package ru.adanil.weather.ui.screens

sealed class WeatherScreens(val route: String) {
    object MainScreen: WeatherScreens("main_screen")
    object OfflineScreen: WeatherScreens("offline_screen")
    object UserCitiesScreen: WeatherScreens("cities_screen")
    object SearchCitiesScreen: WeatherScreens("search_cities_screen")
}