package com.example.swiggyapplication.domain

import com.google.gson.annotations.SerializedName

data class CityDetailsDto(
    @SerializedName("DailyForecasts") val dailyForecasts: List<DailyForecast>,
    @SerializedName("Headline")val headline: Headline
)