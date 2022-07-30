package ru.adanil.weather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherSnackbar(
    snackbarData: SnackbarData,
    elevation: Dp = 6.dp,
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    modifier: Modifier = Modifier.padding(12.dp),
    contentColor: Color = WeatherTheme.color.onSecondary,
    backgroundColor: Color = WeatherTheme.color.onBackground,
    actionColor: Color = SnackbarDefaults.primaryActionColor,
    content: @Composable () -> Unit = { Text(snackbarData.message) },
    action: @Composable (() -> Unit)? = {
        val actionLabel = snackbarData.actionLabel
        if (actionLabel != null) {
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = actionColor),
                onClick = { snackbarData.performAction() },
                content = { Text(actionLabel, fontWeight = FontWeight.Bold) },
            )
        }
    },
) {
    Snackbar(
        shape = shape,
        action = action,
        content = content,
        modifier = modifier,
        elevation = elevation,
        contentColor = contentColor,
        actionOnNewLine = actionOnNewLine,
        backgroundColor = backgroundColor,
    )
}