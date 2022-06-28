package ru.adanil.weather.core.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getById(id: String): CityEntity?

    @Query("SELECT * FROM cities")
    fun getAll(): Flow<List<CityEntity>>

    @Query("DELETE FROM cities WHERE id = :id")
    suspend fun deleteByIdUsers(id: String)

    @Query("SELECT * FROM cities WHERE is_selected = 1")
    suspend fun getSelectedCity(): CityEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg cityEntity: CityEntity)


    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateCity(cityEntity: CityEntity)

}