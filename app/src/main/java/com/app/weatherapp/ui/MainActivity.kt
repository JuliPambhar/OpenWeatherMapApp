package com.app.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.weatherapp.ui.screen.cities.SelectCityScreen
import com.app.weatherapp.ui.screen.weather.ShowWeatherScreen
import com.app.weatherapp.ui.screen.weather.ShowWeatherViewModel
import com.app.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.SELECT_CITY) {
                    composable(
                        route = Routes.SELECT_CITY
                    ) {
                        val cityList = listOf(
                            "Gothenburg",
                            "Stockholm",
                            "Mountain View",
                            "London",
                            "New York",
                            "Berlin"
                        )
                        SelectCityScreen(cityList, onCityClick = {
                            navController.navigate(
                                route = Routes.WEATHER_SCREEN.replace("{name}", it)
                            )
                        })
                    }
                    composable(route = Routes.WEATHER_SCREEN) {
                        val name = it.arguments?.getString("name").orEmpty()
                        val viewModel = hiltViewModel<ShowWeatherViewModel>()

                        ShowWeatherScreen(
                            viewModel = viewModel,
                            cityName = name,
                            onBackPress = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}