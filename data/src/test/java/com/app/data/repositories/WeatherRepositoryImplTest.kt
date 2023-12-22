package com.app.data.repositories

import com.app.data.network.WeatherApi
import com.app.data.network.entities.WeatherData
import com.app.domain.entities.WeatherInfo
import com.app.domain.repositories.IWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    @Mock
    private lateinit var weatherApi: WeatherApi

    private lateinit var weatherRepository: IWeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherRepository = WeatherRepositoryImpl(weatherApi)
    }

    @Test
    fun getWeatherData() {
        runTest {
            val cityName = "xyz"
            val appid = "12345"
            val unit = "abc"
            val fakeWeatherData = getFakeWeatherData()
            val fakeWeatherInfo = getFakeWeatherInfo()
            `when`(weatherApi.getWeatherData(cityName, appid, unit)).then { fakeWeatherData }

            val result = weatherRepository.getWeatherData(cityName, appid, unit)
            assertEquals(result, fakeWeatherInfo)

        }
    }

    private fun getFakeWeatherInfo() = WeatherInfo()
    private fun getFakeWeatherData() = WeatherData(
        base = null,
        clouds = null,
        cod = null,
        coord = null,
        dt = null,
        id = null,
        main = null,
        name = null,
        rain = null,
        sys = null,
        timezone = null,
        visibility = null,
        weather = null,
        wind = null
    )

}