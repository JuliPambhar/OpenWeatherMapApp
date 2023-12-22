package com.app.weatherapp.ui.screen.weather

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.domain.entities.WeatherInfo
import com.app.weatherapp.R
import kotlin.math.roundToInt

@Composable
fun WeatherInfoComponent(weatherInfo: WeatherInfo) {
    Text(
        text = weatherInfo.name,
        modifier = Modifier
            .padding(all = 10.dp),
        style = MaterialTheme.typography.headlineMedium
    )
    Text(
        text = weatherInfo.temp.roundToInt().toString() + "°C",
        style = MaterialTheme.typography.displayMedium
    )

    AsyncImage(
        model = "https://openweathermap.org/img/wn/" + weatherInfo.icon + "@2x.png",
        contentDescription = stringResource(R.string.cd_weather_icon),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    )
    Text(
        text = weatherInfo.main,
        style = MaterialTheme.typography.titleLarge,
    )
}