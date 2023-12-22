package com.app.domain.entities

data class WeatherInfo(
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val temp_max: Double = 0.0,
    val temp_min: Double = 0.0,
    val name: String = "",
    val sunrise: String = "",
    val sunset: String = "",
    val visibility: Int = 0,
    val icon: String = "",
    val main: String = "",
    val speed: Double = 0.0
)