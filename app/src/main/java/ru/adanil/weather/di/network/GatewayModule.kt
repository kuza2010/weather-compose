package ru.adanil.weather.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.gateway.ForecastGateway
import ru.adanil.weather.core.gateway.GeocodingGateway
import ru.adanil.weather.core.gateway.HealthCheckGateway
import ru.adanil.weather.core.gateway.RetrofitFactory
import ru.adanil.weather.core.gateway.WeatherGateway
import java.util.Properties
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GatewayModule {

    @Provides
    @Singleton
    internal fun provideHealthCheckGateway(
        properties: Properties,
        factory: RetrofitFactory
    ): HealthCheckGateway {
        val baseUrl = properties.getProperty("base-url")
        val apiVersion = properties.getProperty("api-version")
        val healthEndpoint = properties.getProperty("health-endpoint")

        return factory.create("$baseUrl$apiVersion$healthEndpoint", HealthCheckGateway::class.java)
    }

    @Provides
    @Singleton
    internal fun provideGeocodingGateway(
        properties: Properties,
        factory: RetrofitFactory
    ): GeocodingGateway {
        val baseUrl = properties.getProperty("base-geocoding-url")
        val apiVersion = properties.getProperty("api-geocoding-version")

        return factory.create("$baseUrl$apiVersion/", GeocodingGateway::class.java)
    }

    @Provides
    @Singleton
    internal fun provideWeatherGateway(
        properties: Properties,
        factory: RetrofitFactory
    ): WeatherGateway {
        val baseUrl = properties.getProperty("base-url")
        val apiVersion = properties.getProperty("api-version")
        val weatherEndpoint = properties.getProperty("weather-endpoint")

        return factory.create("$baseUrl$apiVersion$weatherEndpoint", WeatherGateway::class.java)
    }

    @Provides
    @Singleton
    internal fun provideForecastGateway(
        properties: Properties,
        factory: RetrofitFactory
    ): ForecastGateway {
        val baseUrl = properties.getProperty("base-url")
        val apiVersion = properties.getProperty("api-version")
        val forecastEndpoint = properties.getProperty("forecast-endpoint")

        return factory.create("$baseUrl$apiVersion$forecastEndpoint", ForecastGateway::class.java)
    }
}
