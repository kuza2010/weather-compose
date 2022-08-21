package ru.adanil.weather.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        content = content,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = WeatherTheme.color.secondary,
        )
    )
}
