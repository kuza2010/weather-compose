package ru.adanil.weather.ui.screens.offline

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adanil.weather.R
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun OfflineScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WeatherTheme.color.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_sick_24),
                contentDescription = "error",
                modifier = Modifier
                    .width(220.dp)
                    .height(220.dp)
                    .padding(25.dp),
                colorFilter = ColorFilter.tint(WeatherTheme.color.error)
            )
            Text(
                textAlign = TextAlign.Center,
                color = WeatherTheme.color.error,
                style = MaterialTheme.typography.h6,
                text = stringResource(R.string.error_we_are_sorry)
            )
            Text(
                textAlign = TextAlign.Center,
                color = WeatherTheme.color.error,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = stringResource(R.string.error_server_not_reachable)
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewOfflineActivity() {
    WeatherTheme() {
        OfflineScreen()
    }
}