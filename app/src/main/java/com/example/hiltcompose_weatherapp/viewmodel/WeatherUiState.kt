package com.example.hiltcompose_weatherapp.viewmodel

import com.example.hiltcompose_weatherapp.model.WeatherInfo



// data object is used because it doesn't need any data, its just a singleton state


sealed class WeatherUiState {

    data object Loading: WeatherUiState()
    data class Success(val weatherInfo: WeatherInfo): WeatherUiState()
    data class Error(val errorMessage: String): WeatherUiState()

}

/*

1.) A sealed class in Kotlin is a class that:
                    cannot be inherited outside the file where it is declared
                    allows only a fixed set of child types
                    is used for representing limited UI states, events, or outcomes
                    Think of it like a closed family of classes.

Inside this sealed class, you declare specific allowed states.

So now you have 3 fixed, known UI states:
                                            Loading
                                            Success
                                            Error

This is why sealed classes are used for UI State Management.

NOTE: Normal class caused an error because objects & data classes cannot inherit from a non-sealed, non-open class.


2.)  Normal classes → final → cannot be inherited
     object, data class, or any class cannot extend a final class.

    ✔ Sealed class → explicitly allows inheritance in same file
    That’s why sealed classes must be used to create state hierarchies.

     Super simplified in 1 line:
     Normal classes are final → cannot be extended → so your UIState children (Loading, Success, Error) cannot inherit.
 */