package dev.rasul.weatherapp.dto

import com.google.gson.annotations.SerializedName
import dev.rasul.weatherapp.entity.PlacesEntity

data class PlacesDto(
    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("lon")
    val lon: Double? = null,

    @field:SerializedName("lat")
    val lat: Double? = null,

    @field:SerializedName("state")
    val state: String? = null
) {
    fun toEntity(): PlacesEntity {
        return PlacesEntity(
            country = country ?: "",
            name = name ?: "",
            lon = lon ?: 0.0,
            lat = lat ?: 0.0,
            state = state ?: ""
        )
    }
}

