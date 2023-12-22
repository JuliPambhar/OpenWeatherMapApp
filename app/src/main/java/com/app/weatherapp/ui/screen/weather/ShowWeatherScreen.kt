package com.app.weatherapp.ui.screen.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.app.weatherapp.BuildConfig
import com.app.weatherapp.R
import com.app.weatherapp.utils.UiState
import com.app.weatherapp.widgets.BaseScaffold

@Composable
fun ShowWeatherScreen(
    viewModel: ShowWeatherViewModel,
    cityName: String,
    onBackPress: () -> Unit
) {

    val viewState = viewModel.viewData.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getWeather(cityName, BuildConfig.API_KEY, "metric")
    }

    WeatherComponent(
        viewData = viewState.value,
        onBackPress = onBackPress
    )
}

@Composable
fun WeatherComponent(
    viewData: ShowWeatherViewModel.ViewData,
    onBackPress: () -> Unit
) {
    BaseScaffold(
        title = "",
        onBackPress = onBackPress
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (viewData.state) {
                UiState.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .testTag("viewLoader")
                    )
                }

                UiState.LOADED -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("SuccessView")
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        WeatherInfoComponent(viewData.weatherInfo)

                        WeatherDetailComponent(viewData.weatherInfo)
                    }
                }

                UiState.ERROR -> {
                    Text(
                        text = stringResource(R.string.something_went_wrong),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .testTag("errorView")
                    )
                }
            }
        }
    }
}
