package com.app.data.repositories

import com.app.data.network.WeatherApi
import com.app.data.network.entities.toWeatherInfo
import com.app.domain.entities.WeatherInfo
import com.app.domain.repositories.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : IWeatherRepository {
    override suspend fun getWeatherData(
        cityName: String,
        appId: String,
        unit: String
    ): WeatherInfo {
        return weatherApi.getWeatherData(cityName, appId, unit).toWeatherInfo()
    }
}