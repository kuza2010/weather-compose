package ru.adanil.weather.core.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.adanil.weather.model.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {

    fun getAll(): Flow<List<City>> = cityDao.getAll()
        .map { cityList -> cityList.map { it.toDomain() } }

    suspend fun deleteAll() = cityDao.deleteAll()

}