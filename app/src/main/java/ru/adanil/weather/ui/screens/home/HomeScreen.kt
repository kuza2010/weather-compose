package ru.adanil.weather.ui.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BackdropValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.navigation.WeatherScreens
import ru.adanil.weather.ui.components.SecondaryButton
import ru.adanil.weather.ui.components.WeatherBackdropScaffold
import ru.adanil.weather.ui.theme.WeatherTheme
import ru.adanil.weather.util.ext.navigateSingleTop
import ru.adanil.weather.util.ui.recomposeHighlighter

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsState()
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val scrollState = rememberScrollState()

    val navigateToCitiesScreen = remember(navController) {
        { navController.navigateSingleTop(WeatherScreens.UserCitiesScreen) }
    }

    WeatherBackdropScaffold(
        appBar = {
            HomeAppBar(
                currentCity = uiState.city,
                onAppSettingsClick = {},
                onChangeLocationClick = navigateToCitiesScreen,
                connectionStatus = uiState.connectionStatus
            )
        },
        scaffoldState = backdropState,
        backLayerContent = {
            MainContent(
                weather = uiState.weather,
                loading = uiState.loading,
                onAddButtonClick = navigateToCitiesScreen
            )
        },
        frontLayerContent = {
            ExtraContent(
                scrollState = scrollState
            )
        }
    )
}

@Composable
fun MainContent(
    loading: Boolean,
    weather: CurrentWeather?,
    onAddButtonClick: () -> Unit
) {
    when {
        loading || weather == null -> EmptyWeatherSummary(loading, onAddButtonClick)
        else -> WeatherSummary(weather)
    }
}

@Composable
fun EmptyWeatherSummary(
    loading: Boolean,
    onAddButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Text(
                style = WeatherTheme.typography.h6,
                text = stringResource(R.string.message_select_city_hint)
            )
            SecondaryButton(onAddButtonClick) {
                Text(text = stringResource(R.string.message_select_city_variant))
            }
        }
    }
}

@Composable
fun WeatherSummary(
    weather: CurrentWeather
) {
    Log.e("TESTIN", "WeatherSummary: $weather")
    var visible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            modifier = Modifier.recomposeHighlighter(),
            visible = visible,
            enter = fadeIn(),
        ) {
            Text(
                style = WeatherTheme.typography.h1,
                text = weather.tempSummary.tempDisplay
            )
        }
    }

    LaunchedEffect(Unit) {
        visible = true
    }
}

@Composable
fun ExtraContent(
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp, vertical = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "frontLayerContent")
    }
}
