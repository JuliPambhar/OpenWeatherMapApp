package com.app.weatherapp.ui.screen.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.domain.entities.WeatherInfo
import com.app.weatherapp.R
import com.app.weatherapp.widgets.WeatherDetailCard
import kotlin.math.roundToInt

@Composable
fun WeatherDetailComponent(weatherInfo: WeatherInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(
                text = stringResource(R.string.high_temp, weatherInfo.temp_max.roundToInt()),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = stringResource(R.string.low_temp, weatherInfo.temp_min.roundToInt()),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row {

            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.humidity),
                icon = R.drawable.ic_humidity,
                weatherInfo = stringResource(
                    R.string.humidity_value,
                    weatherInfo.humidity.toString()
                )
            )
            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.wind),
                icon = R.drawable.ic_wind,
                weatherInfo = stringResource(
                    R.string.speed_value,
                    weatherInfo.speed.toString()
                )
            )
            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.sunrise),
                icon = R.drawable.ic_sunrise,
                weatherInfo = weatherInfo.sunrise
            )

        }
        Row {
            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.sunset),
                icon = R.drawable.ic_sunset,
                weatherInfo = weatherInfo.sunset
            )
            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.visibility),
                icon = R.drawable.ic_visibility,
                weatherInfo = stringResource(
                    R.string.visibility_value,
                    weatherInfo.visibility.toString()
                ),
            )
            WeatherDetailCard(
                modifier = Modifier
                    .weight(1f),
                label = stringResource(R.string.pressure),
                icon = R.drawable.ic_pressure,
                weatherInfo = stringResource(
                    R.string.pressure_value,
                    weatherInfo.pressure.toString()
                ),
            )
        }
    }
}