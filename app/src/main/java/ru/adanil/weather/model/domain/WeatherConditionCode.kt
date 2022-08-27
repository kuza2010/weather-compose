package ru.adanil.weather.model.domain

import androidx.annotation.DrawableRes
import ru.adanil.weather.R

enum class WeatherConditionCode(
    val id: Int,
    val main: String,
    val description: String,
    @DrawableRes val iconDay: Int,
    @DrawableRes val iconNight: Int,
) {

    ThunderstormWithLightRain(
        200,
        "Thunderstorm",
        "thunderstorm with light rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithRain(
        201,
        "Thunderstorm",
        "thunderstorm with rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithHeavyRain(
        202,
        "Thunderstorm",
        "thunderstorm with heavy rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    LightThunderstorm(
        210,
        "Thunderstorm",
        "light thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    Thunderstorm(
        211,
        "Thunderstorm",
        "thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    HeavyThunderstorm(
        212,
        "Thunderstorm",
        "heavy thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    RaggedThunderstorm(
        221,
        "Thunderstorm",
        "ragged thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithLightDrizzle(
        230,
        "Thunderstorm",
        "thunderstorm with light drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithDrizzle(
        231,
        "Thunderstorm",
        "thunderstorm with drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithHeavyDrizzle(
        232,
        "Thunderstorm",
        "thunderstorm with heavy drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),

    LightIntensityDrizzle(
        300,
        "Drizzle",
        "light intensity drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    Drizzle(
        301,
        "Drizzle",
        "drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityDrizzle(
        302,
        "Drizzle",
        "heavy intensity drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    LightIntensityDrizzleRain(
        310,
        "Drizzle",
        "light intensity drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    DrizzleRain(
        311,
        "Drizzle",
        "drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityDrizzleRain(
        312,
        "Drizzle",
        "heavy intensity drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerRainAndDrizzle(
        313,
        "Drizzle",
        "shower rain and drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyShowerRainAndDrizzle(
        314,
        "Drizzle",
        "heavy shower rain and drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerDrizzle(
        321,
        "Drizzle",
        "shower drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),

    LightRain(
        500,
        "Rain",
        "light rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    ModerateRain(
        501,
        "Rain",
        "moderate rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    HeavyIntensityRain(
        502,
        "Rain",
        "heavy intensity rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    VeryHeavyRain(
        id = 503,
        main = "Rain",
        description = "very heavy rain",
        iconDay = R.drawable.ic_10d,
        iconNight = R.drawable.ic_10n
    ),
    ExtremeRain(
        504,
        "Rain",
        "extreme rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    FreezingRain(
        511,
        "Rain",
        "freezing rain",
        R.drawable.ic_13d,
        R.drawable.ic_13n
    ),
    LightIntensityShowerRain(
        520,
        "Rain",
        "light intensity shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerRain(
        521,
        "Rain",
        "shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityShowerRain(
        522,
        "Rain",
        "heavy intensity shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    RaggedShowerRain(
        531,
        "Rain",
        "ragged shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),

    LightSnow(600, "Snow", "light snow", R.drawable.ic_13d, R.drawable.ic_13n),
    Snow(601, "Snow", "Snow", R.drawable.ic_13d, R.drawable.ic_13n),
    HeavySnow(602, "Snow", "Heavy snow", R.drawable.ic_13d, R.drawable.ic_13n),
    Sleet(611, "Snow", "Sleet", R.drawable.ic_13d, R.drawable.ic_13n),
    LightShowerSleet(612, "Snow", "Light shower sleet", R.drawable.ic_13d, R.drawable.ic_13n),
    ShowerSleet(613, "Snow", "Shower sleet", R.drawable.ic_13d, R.drawable.ic_13n),
    LightRainAndSnow(615, "Snow", "Light rain and snow", R.drawable.ic_13d, R.drawable.ic_13n),
    RainAndSnow(616, "Snow", "Rain and snow", R.drawable.ic_13d, R.drawable.ic_13n),
    LightShowerSnow(620, "Snow", "Light shower snow", R.drawable.ic_13d, R.drawable.ic_13n),
    ShowerSnow(621, "Snow", "Shower snow", R.drawable.ic_13d, R.drawable.ic_13n),
    HeavyShowerSnow(622, "Snow", "Heavy shower snow", R.drawable.ic_13d, R.drawable.ic_13n),

    Mist(701, "Mist", "mist", R.drawable.ic_50d, R.drawable.ic_50n),
    Smoke(711, "Smoke", "Smoke", R.drawable.ic_50d, R.drawable.ic_50n),
    Haze(721, "Haze", "Haze", R.drawable.ic_50d, R.drawable.ic_50n),
    SandDust(731, "Dust", "sand/dust whirls", R.drawable.ic_50d, R.drawable.ic_50n),
    Fog(741, "Fog", "fog", R.drawable.ic_50d, R.drawable.ic_50n),
    Sand(751, "Sand", "sand", R.drawable.ic_50d, R.drawable.ic_50n),
    Dust(761, "Dust", "dust", R.drawable.ic_50d, R.drawable.ic_50n),
    Volcanic(762, "Ash", "volcanic ash", R.drawable.ic_50d, R.drawable.ic_50n),
    Squalls(771, "Squall", "squalls", R.drawable.ic_50d, R.drawable.ic_50n),
    Tornado(781, "Tornado", "tornado", R.drawable.ic_50d, R.drawable.ic_50n),

    ClearSky(800, "Clear", "clear sky", R.drawable.ic_01d, R.drawable.ic_01d),
    FewClouds(801, "Clouds", "few clouds", R.drawable.ic_02d, R.drawable.ic_02d),
    ScatteredClouds(802, "Clouds", "scattered clouds", R.drawable.ic_03d, R.drawable.ic_03d),
    BrokenClouds(803, "Clouds", "broken clouds", R.drawable.ic_04d, R.drawable.ic_04d),
    OvercastClouds(804, "Clouds", "overcast clouds", R.drawable.ic_04d, R.drawable.ic_04d),

    UNKNOWN(-1, "Unknown", "Unknown", R.drawable.ic_04d, R.drawable.ic_04d);


    companion object {
        fun getWeatherConditionCode(id: Int): WeatherConditionCode {
            return enumValues<WeatherConditionCode>().find { it.id == id }
                ?: UNKNOWN
        }
    }
}