package dev.rasul.weatherapp.data.db.entity

import com.google.gson.Gson

data class PlacesEntity(
    val country: String,

    val name: String,

    val lon: Double,

    val lat: Double,

    val state: String
) {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}

