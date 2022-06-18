package ru.adanil.weather.core.repository

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getAll(): Flow<List<CityEntity>>

    @Query("DELETE FROM cities")
    suspend fun deleteAll()

}