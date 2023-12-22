package com.app.domain.usecase

import com.app.domain.ResponseState
import com.app.domain.entities.WeatherInfo
import com.app.domain.repositories.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val weatherRepository: IWeatherRepository) {
    suspend operator fun invoke(
        cityName: String,
        appId: String,
        unit:String
    ): Flow<ResponseState<WeatherInfo>> {
        return flow {
            try {
                emit(ResponseState.Loading())
                emit(ResponseState.Success(
                        weatherRepository.getWeatherData(
                            cityName,
                            appId,
                            unit
                        )
                    )
                )
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }
    }
}