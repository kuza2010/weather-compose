package ru.adanil.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverService
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverServiceImpl
import ru.adanil.weather.core.service.healthCheck.HealthCheckService
import ru.adanil.weather.core.service.healthCheck.HealthCheckServiceImpl

@Module
@InstallIn(SingletonComponent::class)
interface BindModule {

    @Binds
    fun bindAnalyticsService(analyticsServiceImpl: HealthCheckServiceImpl): HealthCheckService

    @Binds
    fun bindConnectivityService(connectionService: ConnectivityObserverServiceImpl): ConnectivityObserverService

}