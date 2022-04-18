package com.example.orgatrip.feature_trip.presentation.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orgatrip.feature_trip.data.remote.dao.TripRequest

@Composable
fun TripsScreen(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {

    val trips = viewModel.tripsState.value.trips
    val tripToInsert = remember { mutableStateOf(TextFieldValue()) }

    Column {

        TextField(
            value = tripToInsert.value,
            onValueChange = { tripToInsert.value = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val trip1 = TripRequest(name = tripToInsert.value.text)

        Button(onClick = {
            viewModel.createTrip(trip1)
        }
        ) {
            Text(text = "Valider")
        }

        LazyColumn {
            items(trips) {
                Text(text = it.name)
            }
        }
    }
}