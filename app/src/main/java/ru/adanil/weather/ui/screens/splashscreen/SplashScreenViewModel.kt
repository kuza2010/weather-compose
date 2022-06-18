package ru.adanil.weather.ui.screens.splashscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.adanil.weather.R
import ru.adanil.weather.core.service.healthCheck.HealthCheckService
import ru.adanil.weather.model.SnackBarManager
import ru.adanil.weather.ui.screens.WeatherScreens
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val healthCheckService: HealthCheckService
) : ViewModel() {

    fun getNextDestination(): String {
        return when (isApiAvailable()) {
            true -> WeatherScreens.MainScreen.route
            false -> {
                SnackBarManager.showMessage(R.string.error_oops_error_message)
                WeatherScreens.OfflineScreen.route
            }
        }
    }

    private fun isApiAvailable(): Boolean {
        return runBlocking { healthCheckService.isApiAvailable() }
    }

}