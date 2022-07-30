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
        .map { cityList ->
            cityList.toList()
                .map { (city, country) -> city.toDomain(country) }
        }

    suspend fun insertCity(vararg cities: City) {
        val entities = cities.map { it.toEntity() }
        cityDao.insert(*entities.toTypedArray())
    }

    suspend fun getByCityIds(vararg cityIds: String): List<City> {
        return cityDao.getByCityIds(*cityIds)
            .map { (city, country) -> city.toDomain(country) }
    }

    suspend fun selectCity(city: City): CityEntity? {
        return cityDao.getById(city.id)
            ?.takeIf { it.isSelected.not() }
            ?.let {
                // deselect current city
                cityDao.getSelectedCity()?.let { cityDao.updateCity(it.copy(isSelected = false)) }
                cityDao.updateCity(city.copy(isSelected = true).toEntity())
                cityDao.getById(city.id)
            }
    }

    suspend fun delete(city: City) {
        cityDao.deleteCity(city.toEntity())
    }
}
