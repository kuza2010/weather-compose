package ru.adanil.weather.navigation

sealed class WeatherScreens(val route: String) {
    object MainScreen : WeatherScreens("main_screen")
    object OfflineScreen : WeatherScreens("offline_screen")
    object UserCitiesScreen : WeatherScreens("cities_screen")
    object SearchCitiesScreen : WeatherScreens("cities_screen/search_cities_screen")
}
