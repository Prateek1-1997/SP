package com.example.swiggyapplication.domain

interface ICityRepository {

    suspend fun getCitiesBySearch(query:String) : List<CityDtoItem>?

    suspend fun getIndividualCityDetails(key:String) : List<DailyForecast>?
}