package ru.adanil.weather.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.adanil.weather.ui.components.WeatherBackdropScaffold
import ru.adanil.weather.ui.screens.WeatherScreens
import ru.adanil.weather.util.ext.navigateTo

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    exampleViewModel: HomeScreenViewModel
) {
    val uiState: HomeUiState by exampleViewModel.uiState.collectAsState()
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val scrollState = rememberScrollState()

    WeatherBackdropScaffold(
        appBar = {
            HomeAppBar(
                currentCity = uiState.currentCity,
                onAppSettingsClick = {},
                onChangeLocationClick = {
                    navController.navigateTo(WeatherScreens.UserCitiesScreen)
                },
                connectionStatus = uiState.connectionStatus
            )
        },
        scaffoldState = backdropState,
        backLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Text(text = "backLayerContent")
            }
        },
        frontLayerContent = {
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
    )
}
