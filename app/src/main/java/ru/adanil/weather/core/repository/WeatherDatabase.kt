package ru.adanil.weather.core.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CountryEntity::class, CityEntity::class],
    exportSchema = false,
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun countryDao(): CountryDao

    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
                .createFromAsset("database/country.db")
                .build()
        }
    }
}
