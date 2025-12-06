package com.example.hiltcompose_weatherapp.screens

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hiltcompose_weatherapp.viewmodel.WeatherViewModel

@Composable
fun Weatherscreen(
    viewModel: WeatherViewModel = hiltViewModel()
){



}