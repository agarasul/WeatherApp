package dev.rasul.weatherapp.data.network

import dev.rasul.weatherapp.data.network.dto.PlacesDto
import dev.rasul.weatherapp.data.network.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "minutely,alerts",
        @Query("units") units: String = "metric",
    ): Response<WeatherDto>


    @GET("/geo/1.0/direct")
    suspend fun getPossiblePlaces(
        @Query("q") query: String,
        @Query("limit") limit: Int = 10
    ): Response<List<PlacesDto>>
}