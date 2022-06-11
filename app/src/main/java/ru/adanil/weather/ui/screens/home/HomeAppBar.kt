package ru.adanil.weather.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adanil.weather.R
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.ui.components.ConnectivityStatus
import ru.adanil.weather.ui.components.WeatherIconMedium

@Composable
fun HomeAppBar(
    currentCity: String?,
    onAppSettingsClick: (() -> Unit)?,
    onChangeLocationClick: (() -> Unit)?,
    connectionStatus: ConnectionStatus?,
) {
    if (connectionStatus != null) {
        ConnectivityStatus(connectionStatus = connectionStatus)
    }

    AppBar(
        currentCity = currentCity,
        onAppSettingsClick = onAppSettingsClick,
        onChangeLocationClick = onChangeLocationClick
    )
}

@Composable
fun AppBar(
    currentCity: String?,
    onAppSettingsClick: (() -> Unit)?,
    onChangeLocationClick: (() -> Unit)?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { onChangeLocationClick?.invoke() },
        ) {
            WeatherIconMedium(
                contentDescription = "Add new location",
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
            )
        }
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            text = currentCity ?: "Choose your city",
        )
        IconButton(
            onClick = { onAppSettingsClick?.invoke() },
        ) {
            WeatherIconMedium(
                contentDescription = "App settings",
                painter = painterResource(id = R.drawable.ic_baseline_settings_24),
            )
        }
    }
}