package ru.adanil.weather

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.adanil.weather.model.SnackBarManager

@Composable
fun rememberWeatherAppState(
    snackbarManager: SnackBarManager = SnackBarManager,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = LocalContext.current.resources,
): WeatherAppState = remember(scaffoldState, coroutineScope) {
    WeatherAppState(
        resources = resources,
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
    private val resources: Resources,
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
                val messageContent = resources.getString(message.messageId)

                scaffoldState.snackbarHostState.showSnackbar(messageContent)
                snackbarManager.setMessageShown(message.id)
            }
        }
    }

}