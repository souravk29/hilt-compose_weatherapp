package com.example.hiltcompose_weatherapp.screens

import android.R.attr.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hiltcompose_weatherapp.viewmodel.WeatherUiState
import com.example.hiltcompose_weatherapp.viewmodel.WeatherViewModel

//    hiltViewModel() : it is a function that allows us to  inject a viewModel into a composable without need for "ViewModelProvider" or "ViewModelStoreOwner"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
){
    // collect stateflow values as Compose State

    val uiState by viewModel.uiState.collectAsState()
    val locations by viewModel.locations.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()


    Scaffold (
        topBar = { TopAppBar(title = { Text(text = "Weather App With Hilt") }) }
    ){
        padding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LocationSelector(
                locations,
                selectedLocation,
                { location ->
                    viewModel.selectedLocation(location)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // weather display based on ui state
            when(val state = uiState){
                is WeatherUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is WeatherUiState.Success -> {
                    WeatherInfoDisplay(state.weatherInfo)
                }
                is WeatherUiState.Error -> {
                    Text(text = state.errorMessage,
                        color = Color.Red)
                }
            }

        }
    }

}