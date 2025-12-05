package com.example.hiltcompose_weatherapp.repo

import com.example.hiltcompose_weatherapp.model.WeatherInfo

class WeatherRepositoryImpl: WeatherRepository {


    // dummy data for demonstration
    private val weatherData = mapOf(
        "Delhi" to WeatherInfo("16 deg. C","Delhi","it's quite cold here"),
        "Mumbai" to WeatherInfo("27 deg. C","Delhi","it's monsoon here"),
        "Ranchi" to WeatherInfo("20 deg. C","Delhi","it's quite cold here"),
        "Noida" to WeatherInfo("24 deg. C","Delhi","it's cold here"),
        "Chennai" to WeatherInfo("39 deg. C","Delhi","it's hot here")
    )


    // implementing interfaces :

    override fun getWeatherForLocation(location: String): WeatherInfo {
        // return weather data for the given location or return the default value
        return weatherData[location] ?: WeatherInfo("0",location,"Unknown")
    }

    override fun getAllLocation(): List<String> {
        return weatherData.keys.toList()

    }


}