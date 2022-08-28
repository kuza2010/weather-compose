package ru.adanil.weather.model.domain

import java.sql.Timestamp
import kotlin.math.roundToInt

data class CurrentWeather(
    val sys: Sys,
    val wind: Wind,
    val clouds: Clouds,
    val visibility: Int,
    val weather: Weather,
    val cityId: String,
    val cityName: String,
    val coordinate: Coordinate,
    val tempSummary: TempSummary,
    val temperatureUnit: TemperatureUnit,
)

data class Clouds(
    val all: Int,
)

data class Wind(
    val deg: Double,
    val speed: Double,
) {
    val windCondition: WindDirection
        get() = WindDirection.getWindFromWindDegree(deg)

    fun displaySpeed(temperatureUnit: TemperatureUnit): String {
        return when (temperatureUnit) {
            TemperatureUnit.standard,
            TemperatureUnit.metric -> "${speed.toInt()} m/s"
            TemperatureUnit.imperial -> "${speed.toInt()} mi/h"
        }
    }
}

data class Weather(
    val id: Int,
    val icon: String,
    val weather: String,
    val description: String,
) {
    val weatherConditionCode: WeatherConditionCode
        get() = WeatherConditionCode.getWeatherConditionCode(id)
}

data class Sys(
    val sunset: Long,
    val sunrise: Long,
) {
    val sunsetTimestamp: Timestamp
        get() = Timestamp(sunset * 1000)
    val sunriseTimestamp: Timestamp
        get() = Timestamp(sunrise * 1000)
}

data class TempSummary(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
    val feels_like: Double,
) {
    val tempDisplay: String
        get() = "${temp.roundToInt()}°"
    val temp_minDisplay: String
        get() = "${temp_min.roundToInt()}°"
    val temp_maxDisplay: String
        get() = "${temp_max.roundToInt()}°"
    val pressureDisplay: String
        get() = "${pressure.roundToInt()}hPa"
    val humidityDisplay: String
        get() = "${humidity.roundToInt()}%"
    val feels_likeDisplay: String
        get() = "${feels_like.roundToInt()}°"
}
