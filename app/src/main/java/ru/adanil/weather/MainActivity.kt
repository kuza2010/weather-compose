package ru.adanil.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.util.LoggerTagUtil
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var healthCheckService: HealthCheckService

    private val TAG = LoggerTagUtil.getTag<MainActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { true }

        val res = healthCheckService.isApiAvailable()
        Log.e(TAG, "onCreate: $res")

        finish()
    }

}