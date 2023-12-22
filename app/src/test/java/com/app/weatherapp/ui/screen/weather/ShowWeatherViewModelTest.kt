package com.app.weatherapp.ui.screen.weather

import app.cash.turbine.test
import com.app.domain.ResponseState
import com.app.domain.entities.WeatherInfo
import com.app.domain.usecase.GetWeatherUseCase
import com.app.weatherapp.utils.UiState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class ShowWeatherViewModelTest {
    @Mock
    lateinit var getWeatherUseCase: GetWeatherUseCase

    private lateinit var viewModel: ShowWeatherViewModel

    private val testDispatchers = StandardTestDispatcher()

    private var viewData = ShowWeatherViewModel.ViewData()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatchers)
        viewModel = ShowWeatherViewModel(getWeatherUseCase)
    }

    @Test
    fun testViewModelWithDefaultData() {
        assertEquals(viewData.state, viewModel.viewData.value.state)
        assertEquals(viewData.errorMessage, viewModel.viewData.value.errorMessage)
        assertEquals(viewData.weatherInfo, viewModel.viewData.value.weatherInfo)
    }

    @Test
    fun `when getWeather success, then populate viewData with weatherInfo`() {
        runTest {
            val cityName = "stockholm"
            val appid = "12345"
            val unit = "xyz"

            val fakeWeatherInfo = getFakeWeatherInfo()
            `when`(getWeatherUseCase.invoke(cityName, appid, unit)).thenReturn(
                flowOf(
                    ResponseState.Loading(),
                    ResponseState.Success(fakeWeatherInfo)
                )
            )

            viewModel.getWeather(cityName, appid, unit)

            viewModel.viewData.test {
                var emittedItem = awaitItem()
                assertEquals(UiState.LOADING, emittedItem.state)

                emittedItem = awaitItem()
                assertEquals(UiState.LOADED, emittedItem.state)
                assertEquals(fakeWeatherInfo, emittedItem.weatherInfo)

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `when getWeather Failure, then populate viewData with error state`() {
        runTest {
            val cityName = "stockholm"
            val appid = "12345"
            val unit = "xyz"

            val fakeFailureMsg = "Fake Failure"
            `when`(getWeatherUseCase.invoke(cityName, appid, unit)).thenReturn(
                flowOf(
                    ResponseState.Loading(),
                    ResponseState.Error(Throwable(fakeFailureMsg))
                )
            )

            viewModel.getWeather(cityName, appid, unit)

            viewModel.viewData.test {
                var emittedItem = awaitItem()
                assertEquals(UiState.LOADING, emittedItem.state)

                emittedItem = awaitItem()
                assertEquals(UiState.ERROR, emittedItem.state)

                assertEquals(fakeFailureMsg, emittedItem.errorMessage)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    private fun getFakeWeatherInfo() = WeatherInfo()

    @OptIn(DelicateCoroutinesApi::class)
    @After
    fun close() {
        Dispatchers.shutdown()
    }
}