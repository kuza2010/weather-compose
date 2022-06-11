package ru.adanil.weather.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherIconMedium(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = WeatherTheme.color.onBackground
) {
    WeatherIcon(
        tint = tint,
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .width(32.dp)
            .height(32.dp)
    )
}

@Composable
fun WeatherIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = WeatherTheme.color.onBackground
) {
    Icon(
        tint = tint,
        painter = painter,
        modifier = modifier,
        contentDescription = contentDescription
    )
}