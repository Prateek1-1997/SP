package com.example.swiggyapplication.data

import com.example.swiggyapplication.domain.CityDtoItem
import com.example.swiggyapplication.domain.DailyForecast
import com.example.swiggyapplication.domain.ICityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepoImpl @Inject constructor(val services: WeatherServices) : ICityRepository {
    override suspend fun getCitiesBySearch(query: String): List<CityDtoItem>? {
        return withContext(Dispatchers.IO){
            val response = services.getCitiesBySearch(query)
            if(response.isSuccessful){
                response.body()
            }else{
                emptyList()
            }
        }
    }

    override suspend fun getIndividualCityDetails(key: String): List<DailyForecast>? {
        return withContext(Dispatchers.IO){
            val response = services.getIndividualCityDetails(key)
            if(response.isSuccessful){
                response.body()?.dailyForecasts
            }else{
                emptyList()
            }
        }
    }


}

