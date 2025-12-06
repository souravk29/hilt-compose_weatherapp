package com.example.hiltcompose_weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LocationSelector(
    location: List<String>,
    selectedLocation: String,
    onSelectedLocation: (String) -> Unit
){
    Column {
        Text(
            text = "Select a location",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        location.forEach {
            location ->

            Row (modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (location == selectedLocation),
                    onClick = { onSelectedLocation(location) }
                )
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically

            ){
                RadioButton(
                    selected = ( location == selectedLocation ),
                    onClick = { onSelectedLocation(location) }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = location
                )
            }

        }
    }
}