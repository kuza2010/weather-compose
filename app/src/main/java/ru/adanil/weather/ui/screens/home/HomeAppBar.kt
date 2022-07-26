package ru.adanil.weather.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.adanil.weather.R
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.ui.components.ConnectivityStatus
import ru.adanil.weather.ui.components.WeatherIconMedium
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun HomeAppBar(
    currentCity: City?,
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
    currentCity: City?,
    onAppSettingsClick: (() -> Unit)?,
    onChangeLocationClick: (() -> Unit)?,
) {
    Log.e("TESTIN", "AppBar: $currentCity")
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
                painter = rememberVectorPainter(Icons.Filled.Add),
            )
        }
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            style = WeatherTheme.typography.h5,
            textAlign = TextAlign.Center,
            text = currentCity?.name
                ?: stringResource(id = R.string.message_select_city),
        )
        IconButton(
            onClick = { onAppSettingsClick?.invoke() },
        ) {
            WeatherIconMedium(
                contentDescription = "App settings",
                painter = rememberVectorPainter(Icons.Filled.Settings),
            )
        }
    }
}