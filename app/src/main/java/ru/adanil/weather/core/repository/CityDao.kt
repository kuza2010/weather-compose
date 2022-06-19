package ru.adanil.weather.core.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getById(id: String): CityEntity

    @Query("SELECT * FROM cities")
    fun getAll(): Flow<List<CityEntity>>

    @Transaction
    @Query("DELETE FROM cities WHERE id = :id")
    suspend fun deleteByIdUsers(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: CityEntity)

    @Delete
    suspend fun deleteUsers(vararg users: CityEntity)

}