package ru.adanil.weather.ui.screens.pref

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.adanil.weather.R
import ru.adanil.weather.model.City
import ru.adanil.weather.ui.components.WeatherCard
import ru.adanil.weather.ui.components.WeatherIcon
import ru.adanil.weather.ui.components.WeatherIconMedium
import ru.adanil.weather.ui.components.WeatherTopAppBar
import ru.adanil.weather.ui.theme.Shapes
import ru.adanil.weather.ui.theme.WeatherTheme


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun UserCitiesScreen(
    navController: NavController,
    viewModel: UserCitiesViewModel,
) {
    val uiState: UserCitiesUiState by viewModel.uiState.collectAsState()

    Column() {
        WeatherTopAppBar() {
            IconButton(onClick = { navController.popBackStack() }) {
                WeatherIconMedium(
                    contentDescription = "Go back",
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack)
                )
            }
            Text(
                style = WeatherTheme.typography.h5,
                text = stringResource(id = R.string.title_user_cities)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.cities, { it.id }) { city ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.deleteCity(city)
                        } else {
                         true
                        }
                    }
                )

                SwappableCityCard(
                    city = city,
                    dismissState = dismissState,
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(durationMillis = 300)
                    ),
                    onCityCardClicked = {
                        viewModel.selectCity(city)
                    }
                )
            }
            item {
                WeatherCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onClick = {/* do nothing */ }
                ) {
                    WeatherIcon(
                        painter = rememberVectorPainter(Icons.Filled.AddCircle),
                        contentDescription = "Add new city",
                        modifier = Modifier
                            .padding(12.dp)
                            .width(56.dp)
                            .height(56.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwappableCityCard(
    city: City,
    modifier: Modifier,
    dismissState: DismissState,
    onCityCardClicked: () -> Unit
) {
    SwipeToDismiss(
        state = dismissState,
        modifier = modifier,
        dismissThresholds = { FractionalThreshold(0.3f) },
        directions = setOf(DismissDirection.EndToStart),
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    DismissValue.Default -> WeatherTheme.color.onPrimary
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> WeatherTheme.color.primaryVariant
                }
            )
            val icon = when (direction) {
                DismissDirection.StartToEnd -> null
                DismissDirection.EndToStart -> Icons.Filled.Delete
            }

            val scale by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.Default) 1f else 1.5f)
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }

            if (icon != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, top = 12.dp, end = 12.dp)
                        .background(color, Shapes.medium),
                    contentAlignment = alignment,
                ) {
                    Icon(
                        icon,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .scale(scale)
                            .padding(end = 12.dp)
                    )
                }
            }
        },
        dismissContent = {
            CityCardContent(city, onCityCardClicked)
        }
    )
}

@Composable
fun CityCardContent(
    city: City,
    onCityCardClicked: () -> Unit
) {
    WeatherCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, end = 12.dp),
        onClick = { onCityCardClicked.invoke() },
        color = if (city.isSelected) WeatherTheme.color.primary else WeatherTheme.color.surface,
    ) {
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(12.dp),
            style = WeatherTheme.typography.h4,
            text = city.name.replaceFirstChar { it.uppercase() }
        )
    }
}