package ru.adanil.weather.ui.components

import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adanil.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherBackdropScaffold(
    peekHeight: Dp = 100.dp,
    modifier: Modifier = Modifier,
    appBar: @Composable () -> Unit,
    gesturesEnabled: Boolean = true,
    persistentAppBar: Boolean = true,
    stickyFrontLayer: Boolean = true,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    headerHeight: Dp = BackdropScaffoldDefaults.HeaderHeight,
    frontLayerBackgroundColor: Color = WeatherTheme.color.surface,
    backLayerBackgroundColor: Color = WeatherTheme.color.background,
    frontLayerShape: Shape = BackdropScaffoldDefaults.frontLayerShape,
    frontLayerElevation: Dp = BackdropScaffoldDefaults.FrontLayerElevation,
    backLayerContentColor: Color = contentColorFor(backLayerBackgroundColor),
    frontLayerContentColor: Color = contentColorFor(frontLayerBackgroundColor),
    frontLayerScrimColor: Color = Color.Unspecified,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed),
) {
    BackdropScaffold(
        appBar = appBar,
        modifier = modifier,
        peekHeight = peekHeight,
        headerHeight = headerHeight,
        snackbarHost = snackbarHost,
        scaffoldState = scaffoldState,
        gesturesEnabled = gesturesEnabled,
        frontLayerShape = frontLayerShape,
        backLayerContent = backLayerContent,
        persistentAppBar = persistentAppBar,
        stickyFrontLayer = stickyFrontLayer,
        frontLayerContent = frontLayerContent,
        frontLayerElevation = frontLayerElevation,
        frontLayerScrimColor = frontLayerScrimColor,
        backLayerContentColor = backLayerContentColor,
        frontLayerContentColor = frontLayerContentColor,
        backLayerBackgroundColor = backLayerBackgroundColor,
        frontLayerBackgroundColor = frontLayerBackgroundColor,
    )
}
