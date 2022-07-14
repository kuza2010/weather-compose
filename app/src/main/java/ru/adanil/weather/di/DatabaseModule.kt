package ru.adanil.weather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.adanil.weather.core.repository.CityDao
import ru.adanil.weather.core.repository.PlaceDao
import ru.adanil.weather.core.repository.WeatherDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCityDao(appDatabase: WeatherDatabase): CityDao {
        return appDatabase.cityDao()
    }

    @Singleton
    @Provides
    fun providePlaceDao(appDatabase: WeatherDatabase): PlaceDao {
        return appDatabase.placeDao()
    }

}