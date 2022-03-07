package ru.adanil.weather.dagger

import dagger.Binds
import dagger.Module
import ru.adanil.weather.core.service.HealthCheckService
import ru.adanil.weather.core.service.HealthCheckServiceImpl

@Module
abstract class AppBindingModule {

    @Binds
    abstract fun bindHealthCheckServiceImpl_to_HealthCheckService(service: HealthCheckServiceImpl): HealthCheckService

}