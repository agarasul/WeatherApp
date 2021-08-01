package dev.rasul.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.util.*

@Entity(tableName = "weather")
data class WeatherEntity(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val lastSync: Date? = Date(),

    val current: Current? = null,

    val timezone: String? = null,

    val timezoneOffset: Int? = null,

    val daily: List<DailyItem>? = null,

    val lon: Double? = null,

    val hourly: List<HourlyItem>? = null,

    val lat: Double? = null
) {

    data class WeatherItem(

        val icon: String? = null,

        val description: String? = null,

        val main: String? = null,

        val id: Int? = null
    )

    data class HourlyItem(

        val temp: Double? = null,

        val visibility: Int? = null,

        val clouds: Int? = null,

        val feelsLike: Double? = null,
        val dt: Int? = null,
        val weather: WeatherItem? = null,
        val windSpeed: Double? = null,
    )

    data class Temperature(

        val min: Double? = null,

        val max: Double? = null,

        val eve: Double? = null,

        val night: Double? = null,

        val day: Double? = null,

        val morn: Double? = null
    )

    data class FeelsLike(

        val eve: Double? = null,

        val night: Double? = null,

        val day: Double? = null,

        val morn: Double? = null
    )

    data class DailyItem(


        val sunrise: Int? = null,

        val temperature: Temperature? = null,

        val clouds: Int? = null,

        val feelsLike: FeelsLike? = null,

        val dt: Int? = null,

        val pop: Double? = null,

        val sunset: Int? = null,

        val weather: WeatherItem? = null,

        val windSpeed: Double? = null,

        val rain: Double? = null
    )

    data class Current(

        val sunrise: Int? = null,

        val temp: Double? = null,

        val visibility: Int? = null,

        val uvi: Double? = null,

        val pressure: Int? = null,

        val clouds: Int? = null,

        val feelsLike: Double? = null,

        val dt: Int? = null,

        val windDeg: Int? = null,

        val dewPoint: Double? = null,

        val sunset: Int? = null,

        val weather: WeatherItem? = null,

        val humidity: Int? = null,

        val windSpeed: Double? = null
    )

    override fun toString(): String {
        return Gson().toJson(this)
    }
}