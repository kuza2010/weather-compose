package ru.adanil.weather.core.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.adanil.weather.model.domain.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {

    fun getAll(): Flow<List<City>> = cityDao.getAll()
        .map { cityList -> cityList.map { it.toDomain() } }

    suspend fun insertCity(vararg cities: City) {
        val entities = cities.map { city -> CityEntity(city.id, city.name, true) }
        cityDao.insert(*entities.toTypedArray())
    }

    suspend fun selectCity(city: City): CityEntity? {
        return cityDao.getById(city.id)
            ?.takeIf { it.isSelected.not() }
            ?.let {
                // deselect current city
                cityDao.getSelectedCity()?.let { cityDao.updateCity(it.copy(isSelected = false)) }
                cityDao.updateCity(CityEntity(city.id, city.name, true))
                cityDao.getById(city.id)
            }
    }

    suspend fun delete(city: City) {
        cityDao.deleteCity(city.toEntity())
    }

}