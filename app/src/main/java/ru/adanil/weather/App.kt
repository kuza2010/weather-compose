package ru.adanil.weather

import android.app.Application
import android.content.Context

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

}

val Context.appComponent: AppComponent
    get() {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    }