package ru.adanil.weather.core.service.weather

import android.util.Log
import ru.adanil.weather.core.gateway.WeatherGateway
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.TemperatureUnit
import ru.adanil.weather.util.LoggerTagUtil
import javax.inject.Inject

class WeatherServiceImpl @Inject constructor(
    private val weatherGateway: WeatherGateway
) : WeatherService {

    private val TAG = LoggerTagUtil.getTag<WeatherServiceImpl>()

    override suspend fun currentWeather(
        city: City,
        temperatureUnit: TemperatureUnit
    ): CurrentWeather? {
        val weather = try {
            val response = weatherGateway.currentWeather(
                latitude = city.latitude,
                longitude = city.longitude,
                temperatureUnit = temperatureUnit
            )

            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                null
            }
        } catch (throwable: Throwable) {
            Log.e(TAG, "currentWeather: unexpected error: '${throwable.message}'", throwable)
            null
        }

        return weather?.toDomain()
    }
}
