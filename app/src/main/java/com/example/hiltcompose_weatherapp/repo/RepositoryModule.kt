package com.example.hiltcompose_weatherapp.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module                                                                                     // provider of dependencies
@InstallIn(SingletonComponent::class)                                              // lives for whole lifetime of the app
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(                                                     // The function tells Hilt: “Use WeatherRepositoryImpl whenever WeatherRepository is required.”
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

}





/*

Hilt Module : used to tell hilt how to provide the dependencies that it can't figure out on its own.
                 Also helps Hilt to know how to bind the interfaces to their implementations.

@Binds: tells hilt which implementation to use for the interface

@Singleton : makes sure there is only one instance throughout the app


 */

