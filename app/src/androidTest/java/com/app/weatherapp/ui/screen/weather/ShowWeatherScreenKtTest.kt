package com.app.weatherapp.ui.screen.weather

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.app.weatherapp.R
import com.app.weatherapp.ui.theme.WeatherAppTheme
import com.app.weatherapp.utils.UiState
import org.junit.Rule
import org.junit.Test

class ShowWeatherScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun initView(fakeViewData: ShowWeatherViewModel.ViewData) {
        composeTestRule.setContent {
            WeatherAppTheme {
                WeatherComponent(
                    viewData = fakeViewData,
                    onBackPress = {}
                )
            }
        }
    }

    @Test
    fun showLoaderWhenFetchingWeatherInfo() {
        initView(ShowWeatherViewModel.ViewData(state = UiState.LOADING))
        composeTestRule.onNodeWithTag("viewLoader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SuccessView").assertDoesNotExist()
        composeTestRule.onNodeWithTag("errorView").assertDoesNotExist()
    }

    @Test
    fun showSuccessViewWhenFetchingWeatherInfo() {
        initView(ShowWeatherViewModel.ViewData(state = UiState.LOADED))
        composeTestRule.onNodeWithTag("viewLoader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("SuccessView").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorView").assertDoesNotExist()
    }

    @Test
    fun showErrorViewWhenFetchingWeatherInfo() {
        initView(ShowWeatherViewModel.ViewData(state = UiState.ERROR))
        composeTestRule.onNodeWithTag("viewLoader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("SuccessView").assertDoesNotExist()
        composeTestRule.onNodeWithTag("errorView").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorView")
            .assertTextEquals(context.getString(R.string.something_went_wrong))
    }
}