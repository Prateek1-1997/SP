package com.example.swiggyapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiggyapplication.domain.CityDtoItem
import com.example.swiggyapplication.domain.DailyForecast
import com.example.swiggyapplication.domain.ICityRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val iCityRepository: ICityRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(CityState())

     val uiState : StateFlow<CityState>
        get() = _uiState

    private val _cityUiState = MutableStateFlow(IndividualCityState())

    val uiCityState : StateFlow<IndividualCityState>
        get() = _cityUiState


    fun getCitiesBySearch(query: String){
        viewModelScope.launch {
            val response = iCityRepository.getCitiesBySearch(query)
            _uiState.update { currentState->
                currentState.copy(
                    cityList = response
                )
            }
        }
    }

    fun getIndividualCityDetails(key: String){
        viewModelScope.launch {
            val response = iCityRepository.getIndividualCityDetails(key)
            _cityUiState.update { currentState->
                currentState.copy(
                    cityDetails = response

                )
            }
            }
        }
    }



data class CityState (
    val cityList : List<CityDtoItem>? = null
)

data class IndividualCityState (
    val cityDetails : List<DailyForecast>? = null
)
