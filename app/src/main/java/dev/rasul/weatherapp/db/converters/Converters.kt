package dev.rasul.weatherapp.db.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.rasul.weatherapp.entity.WeatherEntity
import java.util.*

@ProvidedTypeConverter
class Converters {


    @TypeConverter
    fun fromWeatherItemJson(value: String): WeatherEntity.WeatherItem? {
        return Gson().fromJson(value, WeatherEntity.WeatherItem::class.java)
    }

    @TypeConverter
    fun weatherItemToJson(weatherItem: WeatherEntity.WeatherItem): String? {
        return Gson().toJson(weatherItem)
    }


    @TypeConverter
    fun fromWeatherListJson(value: String): List<WeatherEntity.WeatherItem?> {
        return Gson().fromJson(value, object : TypeToken<List<WeatherEntity.WeatherItem>>() {}.type)
    }

    @TypeConverter
    fun weatherListToJson(weatherItem: List<WeatherEntity.WeatherItem>): String? {
        return Gson().toJson(weatherItem)
    }

    @TypeConverter
    fun fromHourlyItemJson(value: String): List<WeatherEntity.HourlyItem>? {
        return Gson().fromJson(value, object : TypeToken<List<WeatherEntity.HourlyItem>>() {}.type)
    }

    @TypeConverter
    fun hourlyItemToJson(hourlyItem: List<WeatherEntity.HourlyItem>): String? {
        return Gson().toJson(hourlyItem)
    }

    @TypeConverter
    fun fromTemperatureJson(value: String): WeatherEntity.Temperature? {
        return Gson().fromJson(value, WeatherEntity.Temperature::class.java)
    }

    @TypeConverter
    fun temperatureToJson(hourlyItem: WeatherEntity.HourlyItem): String? {
        return Gson().toJson(hourlyItem)
    }


    @TypeConverter
    fun fromFeelsLikeJson(value: String): WeatherEntity.FeelsLike? {
        return Gson().fromJson(value, WeatherEntity.FeelsLike::class.java)
    }

    @TypeConverter
    fun feelsLikeToJson(feelsLikeItem: WeatherEntity.FeelsLike): String? {
        return Gson().toJson(feelsLikeItem)
    }


    @TypeConverter
    fun fromDailyItemJson(value: String): List<WeatherEntity.DailyItem>? {
        return Gson().fromJson(value, object : TypeToken<List<WeatherEntity.DailyItem>>() {}.type)
    }

    @TypeConverter
    fun dailyItemToJson(dailyItem: List<WeatherEntity.DailyItem>): String? {
        return Gson().toJson(dailyItem)
    }


    @TypeConverter
    fun fromCurrentItemJson(value: String): WeatherEntity.Current? {
        return Gson().fromJson(value, WeatherEntity.Current::class.java)
    }

    @TypeConverter
    fun currentItemToJson(dailyItem: WeatherEntity.Current): String? {
        return Gson().toJson(dailyItem)
    }


    @TypeConverter
    fun fromDateToLong(value: Date?): Long? {
        return value?.time
    }

    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return value?.let { Date(value) }
    }
}