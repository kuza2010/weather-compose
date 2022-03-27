package ru.adanil.weather.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = WeatherTheme.color.onBackground,
    contentColor: Color = WeatherTheme.color.onSecondary,
    actionColor: Color = WeatherTheme.color.secondary,
    elevation: Dp = 6.dp
) {
    Snackbar(
        shape = shape,
        modifier = modifier,
        elevation = elevation,
        actionColor = actionColor,
        snackbarData = snackbarData,
        contentColor = contentColor,
        actionOnNewLine = actionOnNewLine,
        backgroundColor = backgroundColor,
    )
}