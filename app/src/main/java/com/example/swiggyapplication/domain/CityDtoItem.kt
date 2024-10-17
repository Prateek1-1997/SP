package com.example.swiggyapplication.domain

import com.google.gson.annotations.SerializedName

data class CityDtoItem(
    @SerializedName("key") val key: String,
    @SerializedName("LocalizedName") val LocalizedName: String,
    @SerializedName("PrimaryPostalCode") val PrimaryPostalCode: String,
    @SerializedName("Rank") val Rank: Int,
)