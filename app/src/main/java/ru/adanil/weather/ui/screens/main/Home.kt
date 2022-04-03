package ru.adanil.weather.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.components.WeatherTopAppBar

@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val scrollState = rememberScrollState()
    BackdropScaffold(
        scaffoldState = backdropState,
        appBar = {

        },
        backLayerContent = {
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
            Text(text = "backLayerContent")
        },
        frontLayerContent = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
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
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                    Text(text = "frontLayerContent")
                }
            }
        }
    )
}