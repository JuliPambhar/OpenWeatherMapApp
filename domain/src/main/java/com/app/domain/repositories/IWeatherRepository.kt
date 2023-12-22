package com.app.domain.repositories

import com.app.domain.entities.WeatherInfo

interface IWeatherRepository {
    suspend fun getWeatherData(cityName: String, appId: String, unit: String): WeatherInfo
}