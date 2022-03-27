package ru.adanil.weather.ui.screens.splashscreen

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.ui.screens.WeatherScreens

class SplashScreenViewModel @AssistedInject constructor(
    private val healthCheckService: HealthCheckService
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): SplashScreenViewModel
    }

    fun getNextDestination(): String {
        return when (isApiAvailable()) {
            true -> WeatherScreens.MainScreen.route
            false -> WeatherScreens.OfflineScreen.route
        }
    }

    private fun isApiAvailable(): Boolean {
        return healthCheckService.isApiAvailable()
    }

}