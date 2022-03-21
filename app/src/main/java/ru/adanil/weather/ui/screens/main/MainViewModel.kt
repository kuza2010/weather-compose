package ru.adanil.weather.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.ui.screens.WeatherScreens

class MainViewModel @AssistedInject constructor(
    private val healthCheckService: HealthCheckService
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }

    fun getNextDestination(): String {
        return when (isServiceAvailable()) {
            true -> WeatherScreens.MainScreen.route
            false -> WeatherScreens.OfflineScreen.route
        }
    }

    private fun isServiceAvailable(): Boolean {
        return healthCheckService.isApiAvailable()
    }

}