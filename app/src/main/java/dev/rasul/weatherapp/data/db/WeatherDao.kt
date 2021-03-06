package dev.rasul.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.rasul.weatherapp.data.db.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getWeather(): WeatherEntity?

    @Insert
    suspend fun insertAll(vararg users: WeatherEntity)

    @Query("DELETE FROM weather")
    fun deleteAll()
}