package ru.adanil.weather.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Primary900 = Color(0xFF3F207D)
val Primary800 = Color(0xFF4F2B8D)
val Primary700 = Color(0xFF593196)
val Primary600 = Color(0xFF65399F)
val Primary500 = Color(0xFF6D3EA5)
val Primary400 = Color(0xFF825AB2)
val Primary300 = Color(0xFF9877C0)
val Primary200 = Color(0xFFB49ED2)
val Primary100 = Color(0xFFD2C5E4)
val Primary50 = Color(0xFFEDE7F4)

val Secondary900 = Color(0xFF3A6D0E)
val Secondary800 = Color(0xFF5F8F22)
val Secondary700 = Color(0xFF73a32b)
val Secondary600 = Color(0xFF88b735)
val Secondary500 = Color(0xFF98c73d)
val Secondary400 = Color(0xFFa8cf5c)
val Secondary300 = Color(0xFFb8d87b)
val Secondary200 = Color(0xFFcde3a1)
val Secondary100 = Color(0xFFe1eec6)
val Secondary50 = Color(0xFFf3f8e8)

val DarkBlack = Color(0xFF121212) // Default black #121212
val DarkSurface = Color(0xFF1F1D22) // DarkBlack + Primary200 with 8% transparent
val DarkPrimary0dp = Color(0xFF1F1D22)
val DarkPrimary1dp = Color(0xFF2A292D)
val DarkPrimary2dp = Color(0xFF2F2D32)
val DarkPrimary3dp = Color(0xFF312F33)
val DarkPrimary4dp = Color(0xFF333136)
val DarkPrimary6dp = Color(0xFF38363A)
val DarkPrimary8dp = Color(0xFF3A383D)
val DarkPrimary12dp = Color(0xFF3F3D41)
val DarkPrimary16dp = Color(0xFF403F43)
val DarkPrimary24dp = Color(0xFF434146)

val Error = Color(0xFF3F207D)
val ErrorDark = Color(0xFF8C79B1) // Error color + 40% white transparent

internal val LocalWeatherColors = staticCompositionLocalOf<Colors> {
    error("No Color provided")
}

@Composable
fun ProvideWeatherColors(
    colors: Colors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }
    CompositionLocalProvider(LocalWeatherColors provides colorPalette, content = content)
}
