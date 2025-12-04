package com.example.hiltcompose_weatherapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// add to the manifest

@HiltAndroidApp
class WeatherApplication: Application() {

}