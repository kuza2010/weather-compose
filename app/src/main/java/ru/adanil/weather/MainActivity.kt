package ru.adanil.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.ui.WeatherNavigation
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.theme.WeatherTheme
import ru.adanil.weather.util.LoggerTagUtil
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var healthCheckService: HealthCheckService

    private val TAG = LoggerTagUtil.getTag<MainActivity>()
    private var showSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { showSplashScreen }

        val isAvailable = isServiceAvailable()
        showSplashScreen = false

        setContent {
            WeatherTheme {
                val navHostController = rememberNavController()
                val startDestination = getStartDestination(isAvailable)
                WeatherNavigation(navHostController, startDestination)
            }
        }
    }

    private fun isServiceAvailable(): Boolean {
        val isAvailable = healthCheckService.isApiAvailable()
        Log.e(TAG, "isServiceAvailable: $isAvailable")
        return isAvailable
    }

    private fun getStartDestination(isAvailable: Boolean): String {
        return when (isAvailable) {
            true -> WeatherScreens.MainScreen.route
            false -> WeatherScreens.OfflineScreen.route
        }
    }

}