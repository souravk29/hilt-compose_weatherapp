package com.example.hiltcompose_weatherapp.viewmodel

import android.R
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltcompose_weatherapp.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// "@HiltViewModel" : Tells hilt that this ViewModel should be injectable

@HiltViewModel
class WeatherViewModel @Inject constructor( private val weatherRepository: WeatherRepository ): ViewModel() {                                                                            // this class extends the "ViewModel" =>this class will act as ViewModel

    // UI state
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _locations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _locations.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String>("")
    val selectedLocation: StateFlow<String> = _selectedLocation.asStateFlow()


    init {
        loadLocations()                                                                                                 // load location when viewmodel is created
    }


    private fun loadLocations(){

        viewModelScope.launch {                                                                                            // viewModelScope.launch : starts a background task.
                                                                                                                           // It asks the repository: weatherRepository.getAllLocation().
            val locationList = weatherRepository.getAllLocation()
            _locations.value = locationList

            if (locationList.isNotEmpty()){
                _selectedLocation.value = locationList[0]
                loadWeatherForLocation(locationList[0])
            }
        }
    }

    private fun loadWeatherForLocation(location: String){

        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading

            // in real apps this would be asynchronous call and could be handled bt try and catch block
            val weatherInfo = weatherRepository.getWeatherForLocation(location)
            _uiState.value = WeatherUiState.Success(weatherInfo)

        }
    }

    fun selectedLocation(location: String){
        _selectedLocation.value = location
        loadWeatherForLocation(location)
    }








}






/*

"@Inject constructor(..)" :
                                "@Inject" tells hilt to provide 'weatherRepository', we have already defined how to provide
                                'weatherRepository' via "@Binds" annotation.Hilt can inject it here

 */

/*

viewModelScope is a built-in feature that helps you manage background tasks (like fetching data from the internet or reading a database) safely and automatically.
It is part of Kotlin Coroutines, which is the standard way to handle asynchronous code in Android.

*/

/*

1. The Superpower: Surviving Configuration Changes, This is the most important feature.
                   The Problem: In Android, when you rotate your phone (screen rotation), switch to dark mode, or change language settings, the Activity or Fragment is destroyed and
                   recreated. If you store your data (like the weather temperature) inside the Activity, that data is lost instantly.
                   The ViewModel Solution: A class that extends ViewModel does not get destroyed during these configuration changes. It stays alive in memory.
                   In your code: If you fetch the weather data and store it in WeatherViewModel, and the user rotates the phone, the WeatherViewModel is still there with the data
                   ready. The new Activity simply reconnects to the existing WeatherViewModel.

2. Separation of Concerns (The "Brain" vs. The "Face"):
               Acting as a ViewModel means you are separating the Business Logic from the UI Logic.
               The UI (Activity/Compose): This is the "Face." It should be "dumb." It shouldn't know how to fetch data or calculate Celsius to Fahrenheit. Its only job is to
               display what it is told to display. The ViewModel: This is the "Brain." It makes decisions.
               Activity asks: "User clicked refresh."
               ViewModel acts: "Okay, I will call the WeatherRepository, get the data, format it, and handle any errors."


 */