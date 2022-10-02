package ru.adanil.weather.model.domain

data class Forecast(
    val count: Int,
    val forecast: List<HourForecast>
)

data class HourForecast(
    val timestamp: Long,
    val weather: List<Weather>,
    val tempSummary: TempSummary,
)
