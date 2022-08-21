package ru.adanil.weather.model.domain

data class CurrentWeather(
    val wind: Wind,
    val clouds: Clouds,
    val visibility: Int,
    val weather: List<Weather>,
    val cityId: String,
    val cityName: String,
    val coordinate: Coordinate,
    val tempSummary: TempSummary,
)

data class Clouds(
    val all: Int,
)

data class Wind(
    val deg: String,
    val speed: Double
)

data class Weather(
    val id: Int,
    val icon: String,
    val weather: String,
    val description: String,
)

data class TempSummary(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
    val feels_like: Double,
)
