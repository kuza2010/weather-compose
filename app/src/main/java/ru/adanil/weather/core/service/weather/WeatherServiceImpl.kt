package ru.adanil.weather.core.service.weather

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import ru.adanil.weather.core.gateway.ForecastGateway
import ru.adanil.weather.core.gateway.WeatherGateway
import ru.adanil.weather.di.coroutine.IoDispatcher
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.Forecast
import ru.adanil.weather.model.domain.ResponseWrapper
import ru.adanil.weather.model.domain.TemperatureUnit
import ru.adanil.weather.model.domain.safeApiCall
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(
    private val weatherGateway: WeatherGateway,
    private val forecastGateway: ForecastGateway,
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

    override suspend fun currentForecast(
        count: Int,
        city: City,
        temperatureUnit: TemperatureUnit
    ): ResponseWrapper<Forecast> {
        return safeApiCall(defaultDispatcher) {
            forecastGateway.currentForecast(
                latitude = city.latitude,
                longitude = city.longitude,
                temperatureUnit = temperatureUnit
            ).toDomain()
        }
    }

    override suspend fun currentWeatherWithForecast(
        city: City,
        temperatureUnit: TemperatureUnit
    ): ResponseWrapper<Pair<CurrentWeather, Forecast>> {
        val forecastFlow = flowOf(
            forecastGateway.currentForecast(
                latitude = city.latitude,
                longitude = city.longitude,
                temperatureUnit = temperatureUnit
            ).toDomain()
        )

        val weatherFlow = flowOf(
            weatherGateway.currentWeather(
                latitude = city.latitude,
                longitude = city.longitude,
                temperatureUnit = temperatureUnit
            ).toDomain(temperatureUnit)
        )

        val zipped = forecastFlow.zip(weatherFlow) { forecast, currentWeather ->
            currentWeather to forecast
        }

        return safeApiCall(defaultDispatcher) { zipped.first() }
    }
}
