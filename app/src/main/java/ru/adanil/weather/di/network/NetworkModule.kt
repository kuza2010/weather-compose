package ru.adanil.weather.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import ru.adanil.weather.BuildConfig
import ru.adanil.weather.core.gateway.interceptors.HttpLoggingInterceptor
import ru.adanil.weather.core.gateway.interceptors.WeatherApiInterceptor
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(properties: Properties): OkHttpClient {
        val appid = properties.getProperty("api-key")
        val isDebug = BuildConfig.BUILD_TYPE == "debug"

        return OkHttpClient.Builder()
            .addInterceptor(WeatherApiInterceptor(appid))
            .also { if (isDebug) it.addInterceptor(HttpLoggingInterceptor()) }
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

}