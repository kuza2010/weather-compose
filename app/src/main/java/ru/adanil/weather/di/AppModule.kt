package ru.adanil.weather.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.BuildConfig
import ru.adanil.weather.util.ext.connectivityManager
import java.io.IOException
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideConfig(@ApplicationContext appContext: Context): Properties {
        val properties = Properties()
        try {
            properties.load(appContext.assets.open("application.properties"))
            properties.setProperty("api-key", BuildConfig.API_KEY)
        } catch (e: IOException) {
            throw RuntimeException("Unable to load application properties")
        }
        return properties
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager {
        return appContext.connectivityManager
    }

}