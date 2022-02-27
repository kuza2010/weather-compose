package ru.adanil.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import ru.adanil.weather.ui.theme.DarkSurface
import ru.adanil.weather.ui.theme.DarkWeatherPalette
import ru.adanil.weather.ui.theme.LightWeatherPalette

@Composable
fun BoxThemeAware(
    content: @Composable BoxScope.() -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    Box(
        Modifier.background(if (!darkTheme) DarkWeatherPalette.surface else LightWeatherPalette.surface),
        content = content
    )
}