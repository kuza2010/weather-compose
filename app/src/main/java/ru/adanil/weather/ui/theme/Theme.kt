package ru.adanil.weather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val DarkWeatherPalette = darkColors(
    error = ErrorDark,
    onError = DarkBlack,
    primary = Primary200,
    surface = DarkPrimary1dp,
    onPrimary = DarkBlack,
    onSurface = Primary50,
    onSecondary = DarkBlack,
    secondary = Secondary200,
    background = DarkSurface,
    onBackground = Primary50,
    primaryVariant = Primary700,
    secondaryVariant = Secondary200,
)

val LightWeatherPalette = lightColors(
    error = Error,
    onError = Color.White,
    primary = Primary700,
    surface = Color.White,
    onPrimary = Color.White,
    onSurface = DarkBlack,
    onSecondary = Color.White,
    secondary = Secondary700,
    background = Color.White,
    onBackground = DarkBlack,
    primaryVariant = Primary900,
    secondaryVariant = Secondary900,
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkWeatherPalette
    } else {
        LightWeatherPalette
    }
    val typography = Typography

    ProvideWeatherColors(colors) {
        ProvideWeatherTypography(typography) {
            MaterialTheme(
                colors = colors,
                shapes = Shapes,
                content = content,
                typography = typography
            )
        }
    }
}

object WeatherTheme {
    val color: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalWeatherColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalWeatherTypography.current
}