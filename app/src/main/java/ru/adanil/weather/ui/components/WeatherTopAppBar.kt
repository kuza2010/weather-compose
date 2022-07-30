package ru.adanil.weather.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.adanil.weather.R
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherTopAppBar(
    @StringRes title: Int,
    onNavigationIconClickListener: () -> Unit,
) {
    TopAppBar(
        backgroundColor = WeatherTheme.color.background
    ) {
        IconButton(
            onClick = { onNavigationIconClickListener.invoke() }
        ) {
            WeatherIconMedium(
                tint = contentColorFor(WeatherTheme.color.background),
                painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = "Navigation icon for ${stringResource(title)}"
            )
        }
        Text(
            text = stringResource(title),
            overflow = TextOverflow.Ellipsis,
            style = WeatherTheme.typography.h5,
            color = contentColorFor(WeatherTheme.color.background),
        )
    }
}

@Preview(name = "WeatherTopAppBar Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "WeatherTopAppBar Dark Mode"
)
@Composable
fun PreviewWeatherTopAppBar() {
    WeatherTheme() {
        WeatherTopAppBar(R.string.message_city_removed) {}
    }
}
