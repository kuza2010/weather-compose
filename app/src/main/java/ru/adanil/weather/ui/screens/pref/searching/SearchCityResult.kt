package ru.adanil.weather.ui.screens.pref.searching

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.Country
import ru.adanil.weather.ui.components.WeatherIcon
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun SearchCityResult(
    city: City
) {
    val mainColor =
        if (city.isSelected) WeatherTheme.color.primary else WeatherTheme.color.background

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { /*do nothing */ }
            )
            .background(mainColor)
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        Column() {
            Text(
                text = city.name,
                style = WeatherTheme.typography.h5,
                color = contentColorFor(mainColor),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 6.dp),
            ) {
                Text(
                    text = city.country.name,
                    color = contentColorFor(mainColor),
                    style = WeatherTheme.typography.subtitle2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
                WeatherIcon(
                    tint = contentColorFor(mainColor),
                    contentDescription = "Click to select this city",
                    painter = rememberVectorPainter(Icons.Outlined.Place),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Text(
                    text = city.latLonDisplay,
                    color = contentColorFor(mainColor),
                    style = WeatherTheme.typography.subtitle1,
                )
            }
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewOfflineActivity() {
    WeatherTheme() {
        SearchCityResult(
            City(
                "Moscow",
                20.0,
                Country(
                    id = 1,
                    iso2 = "RU",
                    iso3 = "RUS",
                    region = null,
                    name = "Russia",
                    subregion = null,
                    capital = "Moscow",
                    emoji = "\uD83C\uDDF7\uD83C\uDDFA",
                    emojiU = "\uD83C\uDDF7\uD83C\uDDFA"
                ),
                30.0,
                true
            )
        )
    }
}