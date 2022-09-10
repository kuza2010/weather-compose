package ru.adanil.weather.core.service.weather

import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.ResponseWrapper
import ru.adanil.weather.model.domain.TemperatureUnit

interface WeatherService {
    suspend fun currentWeather(
        city: City,
        temperatureUnit: TemperatureUnit = TemperatureUnit.metric,
    ): ResponseWrapper<CurrentWeather>
}
