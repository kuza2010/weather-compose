package ru.adanil.weather

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.adanil.weather.ui.components.Message
import ru.adanil.weather.ui.components.SnackBarManager

@Composable
fun rememberWeatherAppState(
    snackbarManager: SnackBarManager = SnackBarManager,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): WeatherAppState = remember(scaffoldState, coroutineScope) {
    WeatherAppState(
        navController = navController,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        snackbarManager = snackbarManager
    )
}

@Stable
class WeatherAppState(
    coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val snackbarManager: SnackBarManager,
    val navController: NavHostController,
) {
    // Process snackbar's coming from SnackBarManager
    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { messageList ->
                if (messageList.isEmpty()) {
                    return@collect
                }

                val message = messageList.first()

                when (scaffoldState.snackbarHostState.showSnackbar(message)) {
                    SnackbarResult.Dismissed -> snackbarManager.setMessageShown(message.id)
                    SnackbarResult.ActionPerformed -> snackbarManager.messageActionLabelClicked(message)
                }
            }
        }
    }
}

suspend fun SnackbarHostState.showSnackbar(message: Message): SnackbarResult {
    return this.showSnackbar(message.message, message.actionLabel)
}
