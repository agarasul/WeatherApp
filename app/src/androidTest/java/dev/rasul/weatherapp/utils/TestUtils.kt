package dev.rasul.weatherapp.utils

import dev.rasul.weatherapp.data.network.dto.*

object TestUtils {


    fun generateMockWeather(): WeatherDto {
        return WeatherDto(
            current = Current(
                sunrise = 1,
                sunset = 1,
                temp = 0.0,
                visibility = 0,
                uvi = 0.0,
                pressure = 0,
                clouds = 0,
                feelsLike = 0.0,
                dt = 0,
                windDeg = 0,
                windSpeed = 0.0,
                dewPoint = 0.0,
                weather = arrayListOf(
                    WeatherItem(
                        icon = "",
                        description = "",
                        main = "",
                        id = 801
                    )
                ),
                humidity = 0,
            ),
            timezone = "Asia/Baku",
            timezoneOffset = 3600,
            daily = arrayListOf(
                DailyItem(
                    temp = Temp(
                        min = 0.0,
                        max = 0.0,
                        eve = 0.0,
                        night = 0.0,
                        day = 0.0,
                        morn = 0.0
                    ),
                    clouds = 0,
                    feelsLike = FeelsLike(
                        eve = 0.0,
                        night = 0.0,
                        day = 0.0,
                        morn = 0.0
                    ),
                    dt = 0,
                    sunset = 0,
                    weather = arrayListOf(
                        WeatherItem(
                            icon = "",
                            description = "",
                            main = "",
                            id = 801
                        )
                    ),
                    windSpeed = 0.0
                )
            ),
            hourly = arrayListOf(
                HourlyItem(
                    temp = 0.0,
                    visibility = 0,
                    clouds = 0,
                    feelsLike = 0.0,
                    dt = 0,
                    weather = arrayListOf(
                        WeatherItem(
                            icon = "",
                            description = "",
                            main = "",
                            id = 801
                        )
                    ),
                    windSpeed = 0.0
                )
            ),
            lat = 0.0,
            lon = 0.0
        )
    }

}