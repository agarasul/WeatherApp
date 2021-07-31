package dev.rasul.weatherapp.dto

import com.google.gson.annotations.SerializedName
import dev.rasul.weatherapp.entity.WeatherEntity

data class WeatherDto(

    @field:SerializedName("current")
    val current: Current? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,

    @field:SerializedName("daily")
    val daily: List<DailyItem>? = null,

    @field:SerializedName("lon")
    val lon: Double? = null,

    @field:SerializedName("hourly")
    val hourly: List<HourlyItem>? = null,

    @field:SerializedName("lat")
    val lat: Double? = null
) {
    fun toEntity(): WeatherEntity {
        return WeatherEntity(
            current = current?.toEntity(),
            timezone = timezone,
            timezoneOffset = timezoneOffset,
            daily = daily?.map { dailyItem -> dailyItem.toEntity() },
            lon = lon,
            hourly = hourly?.map { hourlyItem -> hourlyItem.toEntity() },
            lat = lat
        )
    }
}

data class WeatherItem(

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("main")
    val main: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) {
    fun toEntity(): WeatherEntity.WeatherItem {
        return WeatherEntity.WeatherItem(
            icon = icon,
            description = description,
            main = main,
            id = id
        )
    }
}

data class HourlyItem(

    @field:SerializedName("temp")
    val temp: Double? = null,

    @field:SerializedName("visibility")
    val visibility: Int? = null,

    @field:SerializedName("clouds")
    val clouds: Int? = null,

    @field:SerializedName("feels_like")
    val feelsLike: Double? = null,

    @field:SerializedName("dt")
    val dt: Int? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem>? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null
) {
    fun toEntity(): WeatherEntity.HourlyItem {
        return WeatherEntity.HourlyItem(
            temp = temp,
            visibility = visibility,
            clouds = clouds,
            feelsLike = feelsLike,
            dt = dt,
            weather = weather?.firstOrNull()?.toEntity(),
            windSpeed = windSpeed
        )
    }
}

data class Temp(

    @field:SerializedName("min")
    val min: Double? = null,

    @field:SerializedName("max")
    val max: Double? = null,

    @field:SerializedName("eve")
    val eve: Double? = null,

    @field:SerializedName("night")
    val night: Double? = null,

    @field:SerializedName("day")
    val day: Double? = null,

    @field:SerializedName("morn")
    val morn: Double? = null
) {
    fun toEntity(): WeatherEntity.Temperature {
        return WeatherEntity.Temperature(
            min = min,
            max = max,
            eve = eve,
            night = night,
            day = day,
            morn = morn
        )
    }
}

data class FeelsLike(

    @field:SerializedName("eve")
    val eve: Double? = null,

    @field:SerializedName("night")
    val night: Double? = null,

    @field:SerializedName("day")
    val day: Double? = null,

    @field:SerializedName("morn")
    val morn: Double? = null
) {
    fun toEntity(): WeatherEntity.FeelsLike {
        return WeatherEntity.FeelsLike(
            eve = eve,
            night = night,
            day = day,
            morn = morn
        )
    }
}

data class DailyItem(

    @field:SerializedName("temp")
    val temp: Temp? = null,

    @field:SerializedName("clouds")
    val clouds: Int? = null,

    @field:SerializedName("feels_like")
    val feelsLike: FeelsLike? = null,

    @field:SerializedName("dt")
    val dt: Int? = null,

    @field:SerializedName("sunset")
    val sunset: Int? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem>? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,
) {

    fun toEntity(): WeatherEntity.DailyItem {
        return WeatherEntity.DailyItem(
            temperature = temp?.toEntity(),
            clouds = clouds,
            feelsLike = feelsLike?.toEntity(),
            dt = dt,
            sunset = sunset,
            weather = weather?.firstOrNull()?.toEntity(),
            windSpeed = windSpeed
        )
    }
}

data class Current(

    @field:SerializedName("sunrise")
    val sunrise: Int? = null,

    @field:SerializedName("temp")
    val temp: Double? = null,

    @field:SerializedName("visibility")
    val visibility: Int? = null,

    @field:SerializedName("uvi")
    val uvi: Double? = null,

    @field:SerializedName("pressure")
    val pressure: Int? = null,

    @field:SerializedName("clouds")
    val clouds: Int? = null,

    @field:SerializedName("feels_like")
    val feelsLike: Double? = null,

    @field:SerializedName("dt")
    val dt: Int? = null,

    @field:SerializedName("wind_deg")
    val windDeg: Int? = null,

    @field:SerializedName("dew_point")
    val dewPoint: Double? = null,

    @field:SerializedName("sunset")
    val sunset: Int? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem>? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null
) {
    fun toEntity(): WeatherEntity.Current {
        return WeatherEntity.Current(
            sunrise = sunrise,
            temp = temp,
            visibility = visibility,
            uvi = uvi,
            pressure = pressure,
            clouds = clouds,
            feelsLike = feelsLike,
            dt = dt,
            windDeg = windDeg,
            dewPoint = dewPoint,
            sunset = sunset,
            weather = weather?.firstOrNull()?.toEntity(),
            humidity = humidity,
            windSpeed = windSpeed
        )
    }
}
