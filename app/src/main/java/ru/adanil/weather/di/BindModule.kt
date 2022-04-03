package ru.adanil.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.core.service.impl.HealthCheckServiceImpl

@Module
@InstallIn(SingletonComponent::class)
interface BindModule {

    @Binds
    fun bindAnalyticsService(analyticsServiceImpl: HealthCheckServiceImpl): HealthCheckService

}