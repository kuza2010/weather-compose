package ru.adanil.weather

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.HiltAndroidApp
import ru.adanil.weather.navigation.WeatherNavHost
import ru.adanil.weather.navigation.WeatherScreens
import ru.adanil.weather.ui.components.WeatherSnackbar
import ru.adanil.weather.ui.theme.WeatherTheme

@HiltAndroidApp
class App : Application()

@Composable
fun WeatherApp(startDestination: String = WeatherScreens.MainScreen.route) {
    WeatherTheme {
        val appState = rememberWeatherAppState()
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    snackbar = { snackbarData ->
                        WeatherSnackbar(snackbarData = snackbarData)
                    }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { padding ->
            WeatherNavHost(
                modifier = Modifier.padding(padding),
                startDestination = startDestination,
                navController = appState.navController
            )
        }
    }
}
