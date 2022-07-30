package ru.adanil.weather.ui.screens.offline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.theme.WeatherTheme
import ru.adanil.weather.util.ext.navigateTo

@Composable
fun OfflineScreen(
    navController: NavController,
    viewModel: OfflineScreenViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isAvailable) {
        if (uiState.isAvailable) {
            navController.navigateTo(WeatherScreens.MainScreen)
        }
    }

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