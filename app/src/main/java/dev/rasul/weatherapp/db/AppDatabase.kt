package dev.rasul.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.rasul.weatherapp.db.converters.Converters
import dev.rasul.weatherapp.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}