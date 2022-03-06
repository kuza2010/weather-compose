package ru.adanil.weather

import android.app.Application
import android.content.Context
import ru.adanil.weather.dagger.AppComponent
import ru.adanil.weather.dagger.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}

val Context.appComponent: AppComponent
    get() {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    }