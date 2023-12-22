package com.app.data.network

import com.app.data.network.entities.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): WeatherData
}