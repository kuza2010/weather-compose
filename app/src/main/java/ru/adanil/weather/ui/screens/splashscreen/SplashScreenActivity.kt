package ru.adanil.weather.ui.screens.splashscreen

import ru.adanil.weather.WeatherApp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.adanil.weather.appComponent
import ru.adanil.weather.extension.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {

    private var showSplashScreen = true
    private val viewModel: SplashScreenViewModel by viewModel {
        appComponent.splashScreenViewModel().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)

        splash.setKeepOnScreenCondition { showSplashScreen }
        val nexScreen = viewModel.getNextDestination()
        showSplashScreen = false

        setContent {
            WeatherApp(nexScreen)
        }
    }

}