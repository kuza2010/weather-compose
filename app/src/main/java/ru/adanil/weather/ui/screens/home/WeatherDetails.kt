package ru.adanil.weather.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.adanil.weather.R
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherDetails(
    currentWeather: CurrentWeather,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(0.3f)
                .height(3.dp)
                .background(WeatherTheme.color.onBackground)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
        ) {
            item { FeelsLikeCard(currentWeather.tempSummary.feels_likeDisplay) }
            item { VisibilityCard(currentWeather.visibilityDisplay) }
            item { HumidityCard(currentWeather.tempSummary.humidityDisplay) }
            item { PressureCard(currentWeather.tempSummary.pressureDisplay) }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Card(
                    elevation = 0.dp,
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    contentColor = contentColorFor(WeatherTheme.color.surface),
                    backgroundColor = WeatherTheme.color.background.copy(alpha = 0.2f),
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "3-hours forecast",
                            style = WeatherTheme.typography.caption,
                        )
                        Text(
                            text = "3-hours forecast will be here soon",
                            style = WeatherTheme.typography.caption,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FeelsLikeCard(feelsLikeTemperature: String) {
    WeatherCard(
        bodyString = feelsLikeTemperature,
        icon = R.drawable.ic_baseline_device_thermostat_24,
        titleString = stringResource(R.string.title_weather_feels_like),
        bottomString = stringResource(R.string.feels_like_description),
    )
}

@Composable
fun VisibilityCard(visibilityDisplay: String) {
    WeatherCard(
        bodyString = visibilityDisplay,
        icon = R.drawable.ic_baseline_visibility_24,
        titleString = stringResource(R.string.title_weather_visibility),
        bottomString = stringResource(R.string.title_weather_visibility_description),
    )
}

@Composable
fun HumidityCard(humidityDisplay: String) {
    WeatherCard(
        bodyString = humidityDisplay,
        icon = R.drawable.ic_baseline_water_drop_24,
        titleString = stringResource(R.string.title_weather_humidity),
        bottomString = stringResource(R.string.title_weather_humidity_description),
    )
}

@Composable
fun PressureCard(humidityDisplay: String, isSquare: Boolean = true) {
    WeatherCard(
        isSquare = isSquare,
        bodyString = humidityDisplay,
        icon = R.drawable.ic_baseline_compress_24,
        titleString = stringResource(R.string.title_weather_pressure),
        bottomString = stringResource(R.string.title_weather_pressure_description),
    )
}

@Composable
fun WeatherCard(
    bodyString: String,
    titleString: String,
    icon: Int? = null,
    isSquare: Boolean = true,
    bottomString: String? = null,
) {
    val text = buildAnnotatedString {
        if (icon != null) {
            appendInlineContent("icon", "[icon]")
            append(titleString)
        } else {
            append(titleString)
        }
    }

    val inlineContent = if (icon != null) mapOf(
        Pair(
            "icon",
            InlineTextContent(
                placeholder = Placeholder(
                    width = WeatherTheme.typography.caption.fontSize,
                    height = WeatherTheme.typography.caption.fontSize,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(painter = painterResource(icon), null)
            }
        )
    ) else {
        emptyMap()
    }

    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(5.dp)
            .let {
                if (isSquare) {
                    return@let it.aspectRatio(1f)
                }
                return@let it
            },
        shape = RoundedCornerShape(10.dp),
        contentColor = contentColorFor(WeatherTheme.color.surface),
        backgroundColor = WeatherTheme.color.background.copy(alpha = 0.2f),
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = text,
                style = WeatherTheme.typography.caption,
                inlineContent = inlineContent
            )
            Text(
                text = bodyString,
                style = WeatherTheme.typography.h4,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .weight(1f)
            )
            if (!bottomString.isNullOrEmpty()) {
                Text(
                    maxLines = 2,
                    text = bottomString,
                    overflow = TextOverflow.Ellipsis,
                    style = WeatherTheme.typography.caption
                )
            }
        }
    }
}
