package ru.adanil.weather.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherCard(
    role: Role? = null,
    elevation: Dp = 0.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    shape: Shape = Shapes.medium,
    border: BorderStroke? = null,
    onClickLabel: String? = null,
    color: Color = MaterialTheme.colors.surface,
    modifier: Modifier = Modifier.fillMaxWidth(),
    contentColor: Color = contentColorFor(color),
    indication: Indication? = LocalIndication.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Surface(
        role = role,
        shape = shape,
        color = color,
        border = border,
        content = content,
        enabled = enabled,
        modifier = modifier,
        elevation = elevation,
        indication = indication,
        contentColor = contentColor,
        onClickLabel = onClickLabel,
        onClick = { onClick.invoke() },
        interactionSource = interactionSource
    )
}