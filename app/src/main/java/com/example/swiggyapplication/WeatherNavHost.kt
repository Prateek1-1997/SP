package com.example.swiggyapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.example.swiggyapplication.presentation.CityViewModel
import kotlinx.serialization.Serializable

@Composable
fun CityNavHost() {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Dashboard
    ){
        composable<Screen.Dashboard> {

            val query = remember { mutableStateOf("") }

            val viewModel = hiltViewModel<CityViewModel>()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()


            Column(
                modifier = Modifier.fillMaxSize().padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                OutlinedTextField(value = query.value, onValueChange = {
                    query.value = it
                    viewModel.getCitiesBySearch(query.value)
                }, maxLines = 1, label = {
                    Text("Search")
                }
                )

                uiState.cityList?.let { cityList ->

                    LazyColumn {

                        items(cityList.size) {

                            Row() {
                                AsyncImage(
                                    model = "https://developer.accuweather.com/sites/default/files/01-s.png",
                                    contentDescription = null,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                                Column {
                                    Text(
                                        cityList[it].key,
                                        modifier = Modifier.clickable {
                                            navHostController.navigate(Screen.CityDetailsScreen(key = cityList[it].key))
                                        })
                                    Text(cityList[it].Rank.toString())
                                }

                            }
                            Spacer(Modifier.padding(vertical = 30.dp))
                        }

                    }

                }
            }
        }
           composable<Screen.CityDetailsScreen> { backStackEntry->

             val profile : Screen.CityDetailsScreen = backStackEntry.toRoute()
             val viewModel = hiltViewModel<CityViewModel>()
             LaunchedEffect(key1 = profile.key) {
                 viewModel.getIndividualCityDetails(profile.key)
             }

            val uiState by viewModel.uiCityState.collectAsStateWithLifecycle()


             Column(modifier = Modifier.fillMaxSize().padding(top = 60.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                 uiState.cityDetails?.let { cityDetails->

                     LazyColumn {

                         items(cityDetails.size){
                             Text("Maximum Temp ${cityDetails[it].Temperature.Maximum}")
                             Text("Minimum Temp ${cityDetails[it].Temperature.Minimum}")
                         }
                     }

                 }

             }
           }

        }


}

sealed class Screen {

     @Serializable
    data object Dashboard : Screen()

    @Serializable
    data class CityDetailsScreen(val key:String) : Screen()
}