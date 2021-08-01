package dev.rasul.weatherapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.rasul.weatherapp.data.db.AppDatabase
import dev.rasul.weatherapp.data.db.WeatherDao
import dev.rasul.weatherapp.data.db.converters.Converters
import dev.rasul.weatherapp.data.db.entity.WeatherEntity
import dev.rasul.weatherapp.utils.TestUtils
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDbUnitTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .addTypeConverter(Converters())
            .build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeWeatherAndRead() {
        val weatherEntity: WeatherEntity = TestUtils.generateMockWeather().toEntity()
        runBlocking {
            weatherDao.insertAll(weatherEntity)
            val savedWeather = weatherDao.getWeather()
            assertThat(savedWeather, equalTo(weatherEntity))
        }
    }
}