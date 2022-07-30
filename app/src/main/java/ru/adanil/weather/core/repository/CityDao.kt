package ru.adanil.weather.core.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE city_id = :id")
    suspend fun getById(id: String): CityEntity?

    @Query("SELECT * FROM cities JOIN country ON cities.country_id = country.id")
    fun getAll(): Flow<Map<CityEntity, CountryEntity>>

    @Query("DELETE FROM cities WHERE city_id = :id")
    suspend fun deleteByCityId(id: String)

    @Query("SELECT * FROM cities WHERE is_selected = 1")
    suspend fun getSelectedCity(): CityEntity?

    @Query("SELECT * FROM cities JOIN country ON cities.country_id = country.id WHERE city_id IN (:ids)")
    suspend fun getByCityIds(vararg ids: String): Map<CityEntity, CountryEntity>

    @Delete
    suspend fun deleteCity(vararg users: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cityEntity: CityEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateCity(cityEntity: CityEntity)
}
