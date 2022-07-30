package ru.adanil.weather.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherCard(
    elevation: Dp = 0.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    shape: Shape = Shapes.medium,
    border: BorderStroke? = null,
    color: Color = MaterialTheme.colors.surface,
    modifier: Modifier = Modifier.fillMaxWidth(),
    contentColor: Color = contentColorFor(color),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Surface(
        shape = shape,
        color = color,
        border = border,
        content = content,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        elevation = elevation,
        contentColor = contentColor,
        interactionSource = interactionSource,
    )
}
