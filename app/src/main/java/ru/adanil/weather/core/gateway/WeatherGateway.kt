package ru.adanil.weather.core.gateway

import retrofit2.http.GET
import retrofit2.http.Query
import ru.adanil.weather.model.domain.TemperatureUnit
import ru.adanil.weather.model.response.CurrentWeatherResponse

interface WeatherGateway {

    @GET("./")
    suspend fun currentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") temperatureUnit: TemperatureUnit,
    ): CurrentWeatherResponse
}
