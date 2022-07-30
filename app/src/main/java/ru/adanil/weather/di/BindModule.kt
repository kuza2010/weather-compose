package ru.adanil.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverService
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverServiceImpl
import ru.adanil.weather.core.service.healthCheck.HealthCheckService
import ru.adanil.weather.core.service.healthCheck.HealthCheckServiceImpl
import ru.adanil.weather.core.service.search.SearchCityService
import ru.adanil.weather.core.service.search.SearchCityServiceImpl

@Module
@InstallIn(SingletonComponent::class)
interface BindModule {

    @Binds
    fun bindSearchCityService(searchCityService: SearchCityServiceImpl): SearchCityService

    @Binds
    fun bindHealthCheckService(analyticsServiceImpl: HealthCheckServiceImpl): HealthCheckService

    @Binds
    fun bindConnectivityService(connectionService: ConnectivityObserverServiceImpl): ConnectivityObserverService
}
