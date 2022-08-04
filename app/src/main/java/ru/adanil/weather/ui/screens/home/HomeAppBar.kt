package ru.adanil.weather.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.adanil.weather.R
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.ui.components.ConnectivityStatus
import ru.adanil.weather.ui.components.WeatherTopAppBar2

@Composable
fun HomeAppBar(
    currentCity: City?,
    onAppSettingsClick: () -> Unit = {},
    onChangeLocationClick: () -> Unit = {},
    connectionStatus: ConnectionStatus?,
) {
    if (connectionStatus != null) {
        ConnectivityStatus(connectionStatus = connectionStatus)
    }

    WeatherTopAppBar2(
        onActionClick = onAppSettingsClick,
        navigationIcon = Icons.Filled.Add,
        actionIcon = Icons.Filled.Settings,
        onNavigationClick = onChangeLocationClick,
        title = currentCity?.name ?: stringResource(id = R.string.message_select_city)
    )
}
