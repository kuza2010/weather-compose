package ru.adanil.weather.model.response

import com.google.gson.annotations.SerializedName
import ru.adanil.weather.model.domain.Forecast
import ru.adanil.weather.model.domain.HourForecast
import ru.adanil.weather.model.domain.TempSummary
import ru.adanil.weather.model.domain.Weather

data class ForecastResponse(
    val cnt: Int,
    @SerializedName("list") val forecast: List<HourForecastResponse>
) {
    fun toDomain(): Forecast {
        return Forecast(
            count = cnt,
            forecast = forecast.map(HourForecastResponse::toDomain)
        )
    }
}

data class HourForecastResponse(
    val weather: List<Weather>,
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val tempSummary: TempSummary,
    // ignore the rest
) {
    fun toDomain(): HourForecast {
        return HourForecast(
            weather = weather,
            timestamp = timestamp,
            tempSummary = tempSummary
        )
    }
}
