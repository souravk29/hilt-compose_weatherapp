package com.example.hiltcompose_weatherapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// add to the manifest

@HiltAndroidApp
class WeatherApplication: Application() {

}


/*

@HiltAndroidApp:

1. Generates a base class that your Application extends
   This base class sets up Hilt’s internal system.

2. Creates the application-level dependency graph
   This is the highest-level container where Hilt stores all singleton dependencies.

3. Automatically injects your @Singleton dependencies.
   eg. @Singleton
        class WeatherRepositoryImpl @Inject constructor(...)

        This instance gets created and stored in the app’s root component.

4. Enables Hilt in all lower-level Android classes'
   After using @HiltAndroidApp, you can now use:
                            @AndroidEntryPoint on Activity/Fragment
                            @HiltViewModel on ViewModel
                            @Inject constructor parameters

 */