package com.app.weatherapp.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.ResponseState
import com.app.domain.entities.WeatherInfo
import com.app.domain.usecase.GetWeatherUseCase
import com.app.weatherapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowWeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _viewData = MutableStateFlow(ViewData())
    val viewData: StateFlow<ViewData> get() = _viewData.asStateFlow()

    fun getWeather(cityName: String, appId: String, unit: String) {
        viewModelScope.launch {
            getWeatherUseCase(cityName, appId, unit)
                .collect { response ->
                    when (response) {
                        is ResponseState.Error -> {
                            _viewData.value = _viewData.value.copy(
                                state = UiState.ERROR,
                                errorMessage = response.throwable.message.orEmpty()
                            )
                        }

                        is ResponseState.Loading -> {
                            _viewData.value = _viewData.value.copy(
                                state = UiState.LOADING
                            )
                        }

                        is ResponseState.Success -> {
                            _viewData.value = _viewData.value.copy(
                                state = UiState.LOADED,
                                weatherInfo = response.data
                            )
                        }
                    }
                }

        }
    }

    data class ViewData(
        val state: UiState = UiState.LOADING,
        val weatherInfo: WeatherInfo = WeatherInfo(),
        val errorMessage: String = ""
    )
}