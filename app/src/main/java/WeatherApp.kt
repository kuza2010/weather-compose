import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.adanil.weather.rememberWeatherAppState
import ru.adanil.weather.ui.components.WeatherSnackbar
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.ui.screens.offline.OfflineActivity
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherApp(
    startDestination: String = WeatherScreens.MainScreen.route
) {
    WeatherTheme {
        val appState = rememberWeatherAppState()
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    snackbar = { snackbarData -> WeatherSnackbar(snackbarData = snackbarData) }
                )
            },
            scaffoldState = appState.scaffoldState
        ) {
            NavHost(
                startDestination = startDestination,
                navController = appState.navController
            ) {
                weatherAppNavGraph()
            }
        }
    }
}

private fun NavGraphBuilder.weatherAppNavGraph() {
    composable(route = WeatherScreens.MainScreen.route) {
        Text(text = "You are on main screen")
    }
    composable(route = WeatherScreens.OfflineScreen.route) {
        OfflineActivity()
    }
}