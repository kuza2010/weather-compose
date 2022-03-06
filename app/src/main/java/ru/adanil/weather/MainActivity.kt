package ru.adanil.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.adanil.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                Text("Hello world")
            }
        }
    }
}