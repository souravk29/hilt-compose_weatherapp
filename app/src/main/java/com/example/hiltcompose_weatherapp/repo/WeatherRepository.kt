package com.example.hiltcompose_weatherapp.repo

import com.example.hiltcompose_weatherapp.model.WeatherInfo

interface WeatherRepository {

    fun getWeatherForLocation(location: String): WeatherInfo        // The function returns an object of type: WeatherInfo => it is a data class
                                                                    // or some custom class representing some custom class defining weather details
    fun getAllLocation(): List<String>

}