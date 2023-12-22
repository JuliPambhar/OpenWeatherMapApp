package com.app.data.network.entities

import com.app.domain.entities.WeatherInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

data class WeatherData(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val rain: Rain?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)

fun WeatherData.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        humidity = main?.humidity ?: 0,
        pressure = main?.pressure ?: 0,
        temp = main?.temp ?: 0.0,
        temp_max = main?.temp_max ?: 0.0,
        temp_min = main?.temp_min ?: 0.0,
        name = name.orEmpty(),
        sunrise = sys?.sunrise?.let { getFormatTime(it.toLong(), timezone ?: 0) } ?: "",
        sunset = sys?.sunset?.let { getFormatTime(it.toLong(), timezone ?: 0) } ?: "",
        visibility = visibility?.div(1000) ?: 0,
        icon = weather?.firstOrNull()?.icon ?: "",
        main = weather?.firstOrNull()?.main ?: "",
        speed = wind?.speed ?: 0.0
    )
}

fun getFormatTime(timeStamp: Long, offsetSeconds: Int): String? {
    val cal = Calendar.getInstance()
    cal.timeInMillis = timeStamp * 1000L
    cal.add(Calendar.SECOND, offsetSeconds)

    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(cal.time)
}