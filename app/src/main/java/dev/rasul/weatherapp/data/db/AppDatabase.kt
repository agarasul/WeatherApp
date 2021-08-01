package dev.rasul.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.rasul.weatherapp.data.db.converters.Converters
import dev.rasul.weatherapp.data.db.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}