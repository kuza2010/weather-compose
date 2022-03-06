package ru.adanil.weather.dagger.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.adanil.weather.BuildConfig
import ru.adanil.weather.core.gateway.interceptors.HttpLoggingInterceptor
import ru.adanil.weather.core.gateway.interceptors.WeatherApiInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
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