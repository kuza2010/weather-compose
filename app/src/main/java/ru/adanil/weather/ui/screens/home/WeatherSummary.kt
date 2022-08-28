package ru.adanil.weather.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.adanil.weather.R
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.ui.components.SecondaryButton
import ru.adanil.weather.ui.components.WeatherIconMedium
import ru.adanil.weather.ui.components.WeatherIconZoomed
import ru.adanil.weather.ui.theme.Shapes
import ru.adanil.weather.ui.theme.WeatherTheme
import ru.adanil.weather.util.ext.TimeFormatter
import ru.adanil.weather.util.ext.format
import java.sql.Timestamp

@Composable
fun EmptyWeatherSummary(
    loading: Boolean,
    onAddButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Text(
                style = WeatherTheme.typography.h6,
                text = stringResource(R.string.message_select_city_hint)
            )
            SecondaryButton(onAddButtonClick) {
                Text(text = stringResource(R.string.message_select_city_variant))
            }
        }
    }
}

@Composable
fun WeatherSummary(
    currentWeather: CurrentWeather,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var visible by remember { mutableStateOf(false) }
    val weatherIcon = remember {
        if (isSystemInDarkTheme) {
            currentWeather.weather.weatherConditionCode.iconNight
        } else {
            currentWeather.weather.weatherConditionCode.iconDay
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 1000)
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainSummary(
                    weatherIcon = weatherIcon,
                    currentTemperature = currentWeather.tempSummary.tempDisplay,
                    weatherIconContentDescription = currentWeather.weather.description,
                )
                SummaryTable(
                    sunset = currentWeather.sys.sunsetTimestamp,
                    sunrise = currentWeather.sys.sunriseTimestamp,
                    windIcon = currentWeather.wind.windCondition.icon,
                    humidity = currentWeather.tempSummary.humidityDisplay,
                    minTemperature = currentWeather.tempSummary.temp_minDisplay,
                    maxTemperature = currentWeather.tempSummary.temp_maxDisplay,
                    weatherDescription = currentWeather.weather.weatherConditionCode.description,
                    windSpeed = currentWeather.wind.displaySpeed(currentWeather.temperatureUnit),
                )
            }
        }

        LaunchedEffect(Unit) {
            visible = true
        }
    }
}

@Composable
fun MainSummary(
    currentTemperature: String,
    @DrawableRes weatherIcon: Int,
    weatherIconContentDescription: String = "",
) {
    Row {
        Text(
            text = currentTemperature,
            style = WeatherTheme.typography.h1,
        )
        WeatherIconZoomed(
            tint = Color.Unspecified,
            modifier = Modifier.align(Alignment.Bottom),
            painter = painterResource(id = weatherIcon),
            contentDescription = weatherIconContentDescription
        )
    }
}

@Composable
fun SummaryTable(
    humidity: String,
    sunset: Timestamp,
    windSpeed: String,
    sunrise: Timestamp,
    minTemperature: String,
    maxTemperature: String,
    weatherDescription: String,
    @DrawableRes windIcon: Int,
) {
    Surface(
        shape = Shapes.medium,
        modifier = Modifier
            .padding(top = 30.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth(0.8f)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = weatherDescription,
                textAlign = TextAlign.Center,
                style = WeatherTheme.typography.h6,
                modifier = Modifier.fillMaxWidth(),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(2.dp)
                    .background(WeatherTheme.color.primaryVariant)
            )
            LazyRow(
                userScrollEnabled = false,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    TaleItem(
                        bottomText = "Sunrise",
                        icColor = Color.Unspecified,
                        icon = R.drawable.ic_sunrise,
                        headLineText = sunrise.format(TimeFormatter.HMTimeFormatter)
                    )
                }
                item {
                    TaleItem(
                        bottomText = "Min",
                        headLineText = minTemperature,
                        icon = R.drawable.ic_freez_24dp
                    )
                }
                item {
                    TaleItem(
                        bottomText = "Max",
                        headLineText = maxTemperature,
                        icon = R.drawable.ic_hot_24dp
                    )
                }
                item {
                    TaleItem(
                        bottomText = "Humidity",
                        headLineText = humidity,
                        icon = R.drawable.ic_humidity_24dp
                    )
                }
                item {
                    TaleItem(
                        icon = windIcon,
                        bottomText = "Wind",
                        headLineText = windSpeed
                    )
                }
                item {
                    TaleItem(
                        bottomText = "Sunset",
                        icColor = Color.Unspecified,
                        icon = R.drawable.ic_sunset,
                        headLineText = sunset.format(TimeFormatter.HMTimeFormatter)
                    )
                }
            }
        }
    }
}

@Composable
fun TaleItem(
    bottomText: String,
    headLineText: String,
    @DrawableRes icon: Int,
    contentDescriptor: String = headLineText + bottomText,
    icColor: Color = contentColorFor(backgroundColor = WeatherTheme.color.background)
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = headLineText,
            fontWeight = FontWeight.Thin,
            style = WeatherTheme.typography.body2,
        )
        WeatherIconMedium(
            tint = icColor,
            painter = painterResource(icon),
            contentDescription = contentDescriptor
        )
        Text(
            text = bottomText,
            fontWeight = FontWeight.Thin,
            style = WeatherTheme.typography.body2
        )
    }
}
