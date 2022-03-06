package ru.adanil.weather.dagger.modules

import dagger.Module
import dagger.Provides
import ru.adanil.weather.core.gateway.HealthCheckGateway
import ru.adanil.weather.core.gateway.RetrofitFactory
import java.util.*

@Module
class GatewayModule {

    @Provides
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