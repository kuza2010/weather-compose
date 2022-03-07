package ru.adanil.weather.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.adanil.weather.BuildConfig
import ru.adanil.weather.dagger.modules.GatewayModule
import ru.adanil.weather.dagger.modules.NetworkModule
import java.io.IOException
import java.util.*

@Module(includes = [NetworkModule::class, GatewayModule::class, AppBindingModule::class])
class AppModule {

    @Provides
    fun provideConfig(appContext: Context): Properties {
        val properties = Properties()
        try {
            properties.load(appContext.assets.open("application.properties"))
            properties.setProperty("api-key", BuildConfig.API_KEY)
        } catch (e: IOException) {
            throw RuntimeException("Unable to load application properties")
        }
        return properties
    }

}