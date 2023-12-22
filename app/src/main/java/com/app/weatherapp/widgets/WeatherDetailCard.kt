package com.app.weatherapp.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.weatherapp.R

@Composable
fun WeatherDetailCard(modifier: Modifier, label: String, icon: Int, weatherInfo: String) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(
            topEnd = 10.dp,
            topStart = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
        modifier = modifier
            .height(140.dp)
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(all = 10.dp),
                text = label,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.ic_sunset),
            )
            Text(
                modifier = Modifier
                    .padding(all = 10.dp),
                text = weatherInfo,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}