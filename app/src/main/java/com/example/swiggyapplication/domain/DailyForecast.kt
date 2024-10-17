package com.example.swiggyapplication.domain

import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("Date") val Date: String,
    @SerializedName("Day") val Day: Day,
    @SerializedName("EpochDate") val EpochDate: Int,
    @SerializedName("Link") val Link: String,
    @SerializedName("MobileLink")val MobileLink: String,
    @SerializedName("Night")val Night: Night,
    @SerializedName("Sources") val Sources: List<String>,
    @SerializedName("Temperature")val Temperature: Temperature
)