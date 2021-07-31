package dev.rasul.weatherapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rasul.weatherapp.db.AppDatabase
import dev.rasul.weatherapp.db.WeatherDao
import dev.rasul.weatherapp.db.converters.Converters

@Module
@InstallIn(ActivityComponent::class)
object RoomModule {


    @Provides
    fun provideRoomDb(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "weather_db")
            .addTypeConverter(Converters())
            .build()
    }

    @Provides
    fun provideWeatherDao(
        db: AppDatabase
    ): WeatherDao {
        return db.weatherDao()
    }
}