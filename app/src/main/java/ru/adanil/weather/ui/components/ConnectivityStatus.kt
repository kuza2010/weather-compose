package ru.adanil.weather.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.util.ui.connectivityState

@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus() {
    val connection by connectivityState()

    ConnectivityStatus(connection)
}


@Composable
fun ConnectivityStatus(connectionStatus: ConnectionStatus) {
    var visibility by remember { mutableStateOf(false) }
    val message =
        if (ConnectionStatus.CONNECTED == connectionStatus) "Backing online..." else "No internet connection"

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Text(
            message,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }

    LaunchedEffect(connectionStatus) {
        if (ConnectionStatus.DISCONNECTED == connectionStatus) {
            visibility = true
        } else {
            delay(5000)
            visibility = false
        }
    }
}