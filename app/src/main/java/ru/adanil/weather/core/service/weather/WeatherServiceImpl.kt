package ru.adanil.weather.core.service.weather

import kotlinx.coroutines.CoroutineDispatcher
import ru.adanil.weather.core.gateway.WeatherGateway
import ru.adanil.weather.di.coroutine.IoDispatcher
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.ResponseWrapper
import ru.adanil.weather.model.domain.TemperatureUnit
import ru.adanil.weather.model.domain.safeApiCall
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(
    private val weatherGateway: WeatherGateway,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : WeatherService {

    override suspend fun currentWeather(
        city: City,
        temperatureUnit: TemperatureUnit
    ): ResponseWrapper<CurrentWeather> {
        return safeApiCall(defaultDispatcher) {
            weatherGateway.currentWeather(
                latitude = city.latitude,
                longitude = city.longitude,
                temperatureUnit = temperatureUnit
            ).toDomain(temperatureUnit)
        }
    }
}
