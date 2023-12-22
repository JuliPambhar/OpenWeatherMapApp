package com.app.data.di

import com.app.data.network.WeatherApi
import com.app.data.repositories.WeatherRepositoryImpl
import com.app.domain.repositories.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): IWeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }
}