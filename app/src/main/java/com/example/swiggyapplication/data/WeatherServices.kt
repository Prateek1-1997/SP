package com.example.swiggyapplication.data

import com.example.swiggyapplication.domain.CityDetailsDto
import com.example.swiggyapplication.domain.CityDtoItem
import com.example.swiggyapplication.domain.DailyForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherServices {

    @GET("locations/v1/cities/search")
    suspend fun getCitiesBySearch(@Query ("q") query :String, @Query ("apikey") apiKey :String ="mnewAQsmLV4JwDRZ878c9JOS0hFEolju") : Response<List<CityDtoItem>>

    @GET("forecasts/v1/daily/5day/{id}")
    suspend fun getIndividualCityDetails(@Path ("id") key :String, @Query ("apikey") apiKey :String ="mnewAQsmLV4JwDRZ878c9JOS0hFEolju") : Response<CityDetailsDto>

}



