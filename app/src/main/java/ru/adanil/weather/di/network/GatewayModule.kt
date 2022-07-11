package ru.adanil.weather.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.gateway.HealthCheckGateway
import ru.adanil.weather.core.gateway.RetrofitFactory
import java.util.*
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

}