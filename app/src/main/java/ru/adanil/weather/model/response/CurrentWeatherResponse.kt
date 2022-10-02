package ru.adanil.weather.model.response

import com.google.gson.annotations.SerializedName
import ru.adanil.weather.model.domain.Clouds
import ru.adanil.weather.model.domain.Coordinate
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.Sys
import ru.adanil.weather.model.domain.TempSummary
import ru.adanil.weather.model.domain.TemperatureUnit
import ru.adanil.weather.model.domain.Weather
import ru.adanil.weather.model.domain.Wind

data class CurrentWeatherResponse(
    val sys: Sys,
    val wind: Wind,
    val clouds: Clouds,
    val visibility: Int,
    val weather: List<Weather>,
    @SerializedName("id") val cityId: String,
    @SerializedName("name") val cityName: String,
    @SerializedName("coord") val coordinate: Coordinate,
    @SerializedName("main") val tempSummary: TempSummary,
) {
    fun toDomain(temperatureUnit: TemperatureUnit): CurrentWeather {
        return CurrentWeather(
            sys = sys,
            wind = wind,
            clouds = clouds,
            cityId = cityId,
            cityName = cityName,
            coordinate = coordinate,
            visibility = visibility,
            tempSummary = tempSummary,
            weather = weather.first(),
            temperatureUnit = temperatureUnit,
        )
    }
}
