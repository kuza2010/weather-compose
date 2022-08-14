package ru.adanil.weather.ui.components

import android.content.res.Configuration
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actionIcon: ImageVector?,
    navigationIcon: ImageVector,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = contentColorFor(WeatherTheme.color.background)
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    tint = contentColorFor(WeatherTheme.color.background),
                    contentDescription = "Navigation icon for $title"
                )
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = actionIcon,
                        tint = contentColorFor(WeatherTheme.color.background),
                        contentDescription = "Action icon for $title"
                    )
                }
            }
        },
        elevation = elevation,
        contentColor = contentColor,
        backgroundColor = WeatherTheme.color.background,
    )
}

@Preview(name = "WeatherTopAppBar2 Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "WeatherTopAppBar Dark Mode"
)
@Composable
fun PreviewWeatherTopAppBar() {
    WeatherTheme() {
        WeatherTopAppBar(
            title = "Top app bar",
            actionIcon = Icons.Default.MoreVert,
            navigationIcon = Icons.Default.Search
        )
    }
}
