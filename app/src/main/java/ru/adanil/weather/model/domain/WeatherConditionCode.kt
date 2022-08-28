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
        "Thunderstorm with light rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithRain(
        201,
        "Thunderstorm",
        "Thunderstorm with rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithHeavyRain(
        202,
        "Thunderstorm",
        "Thunderstorm with heavy rain",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    LightThunderstorm(
        210,
        "Thunderstorm",
        "Light thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    Thunderstorm(
        211,
        "Thunderstorm",
        "Thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    HeavyThunderstorm(
        212,
        "Thunderstorm",
        "Heavy thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    RaggedThunderstorm(
        221,
        "Thunderstorm",
        "Ragged thunderstorm",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithLightDrizzle(
        230,
        "Thunderstorm",
        "Thunderstorm with light drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithDrizzle(
        231,
        "Thunderstorm",
        "Thunderstorm with drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),
    ThunderstormWithHeavyDrizzle(
        232,
        "Thunderstorm",
        "Thunderstorm with heavy drizzle",
        R.drawable.ic_11d,
        R.drawable.ic_11n
    ),

    LightIntensityDrizzle(
        300,
        "Drizzle",
        "Light intensity drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    Drizzle(
        301,
        "Drizzle",
        "Drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityDrizzle(
        302,
        "Drizzle",
        "Heavy intensity drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    LightIntensityDrizzleRain(
        310,
        "Drizzle",
        "Light intensity drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    DrizzleRain(
        311,
        "Drizzle",
        "Drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityDrizzleRain(
        312,
        "Drizzle",
        "Heavy intensity drizzle rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerRainAndDrizzle(
        313,
        "Drizzle",
        "Shower rain and drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyShowerRainAndDrizzle(
        314,
        "Drizzle",
        "Heavy shower rain and drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerDrizzle(
        321,
        "Drizzle",
        "Shower drizzle",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),

    LightRain(
        500,
        "Rain",
        "Light rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    ModerateRain(
        501,
        "Rain",
        "Moderate rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    HeavyIntensityRain(
        502,
        "Rain",
        "Heavy intensity rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    VeryHeavyRain(
        id = 503,
        main = "Rain",
        description = "Very heavy rain",
        iconDay = R.drawable.ic_10d,
        iconNight = R.drawable.ic_10n
    ),
    ExtremeRain(
        504,
        "Rain",
        "Extreme rain",
        R.drawable.ic_10d,
        R.drawable.ic_10n
    ),
    FreezingRain(
        511,
        "Rain",
        "Freezing rain",
        R.drawable.ic_13d,
        R.drawable.ic_13n
    ),
    LightIntensityShowerRain(
        520,
        "Rain",
        "Light intensity shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    ShowerRain(
        521,
        "Rain",
        "Shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    HeavyIntensityShowerRain(
        522,
        "Rain",
        "Heavy intensity shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),
    RaggedShowerRain(
        531,
        "Rain",
        "Ragged shower rain",
        R.drawable.ic_09d,
        R.drawable.ic_09n
    ),

    LightSnow(600, "Snow", "Light snow", R.drawable.ic_13d, R.drawable.ic_13n),
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

    Mist(701, "Mist", "Mist", R.drawable.ic_50d, R.drawable.ic_50n),
    Smoke(711, "Smoke", "Smoke", R.drawable.ic_50d, R.drawable.ic_50n),
    Haze(721, "Haze", "Haze", R.drawable.ic_50d, R.drawable.ic_50n),
    SandDust(731, "Dust", "Sand/dust whirls", R.drawable.ic_50d, R.drawable.ic_50n),
    Fog(741, "Fog", "Fog", R.drawable.ic_50d, R.drawable.ic_50n),
    Sand(751, "Sand", "Sand", R.drawable.ic_50d, R.drawable.ic_50n),
    Dust(761, "Dust", "Dust", R.drawable.ic_50d, R.drawable.ic_50n),
    Volcanic(762, "Ash", "Volcanic ash", R.drawable.ic_50d, R.drawable.ic_50n),
    Squalls(771, "Squall", "Squalls", R.drawable.ic_50d, R.drawable.ic_50n),
    Tornado(781, "Tornado", "Tornado", R.drawable.ic_50d, R.drawable.ic_50n),

    ClearSky(800, "Clear", "Clear sky", R.drawable.ic_01d, R.drawable.ic_01d),
    FewClouds(801, "Clouds", "Few clouds", R.drawable.ic_02d, R.drawable.ic_02d),
    ScatteredClouds(802, "Clouds", "Scattered clouds", R.drawable.ic_03d, R.drawable.ic_03d),
    BrokenClouds(803, "Clouds", "Broken clouds", R.drawable.ic_04d, R.drawable.ic_04d),
    OvercastClouds(804, "Clouds", "Overcast clouds", R.drawable.ic_04d, R.drawable.ic_04d),

    UNKNOWN(-1, "Unknown", "Unknown", R.drawable.ic_04d, R.drawable.ic_04d);

    companion object {
        fun getWeatherConditionCode(id: Int): WeatherConditionCode {
            return enumValues<WeatherConditionCode>().find { it.id == id }
                ?: UNKNOWN
        }
    }
}
