package ru.adanil.weather.core.service.healthCheck

interface HealthCheckService {
    suspend fun isApiAvailable(): Boolean
}
