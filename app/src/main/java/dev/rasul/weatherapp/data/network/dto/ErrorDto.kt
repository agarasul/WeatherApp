package dev.rasul.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ErrorDto(

    @field:SerializedName("cod")
    val cod: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)
