package ru.adanil.weather.core.service.healthCheck

interface HealthCheckService {
    fun isApiAvailable(): Boolean
}