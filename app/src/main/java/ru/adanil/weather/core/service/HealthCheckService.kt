package ru.adanil.weather.core.service

interface HealthCheckService {
    fun isApiAvailable(): Boolean
}