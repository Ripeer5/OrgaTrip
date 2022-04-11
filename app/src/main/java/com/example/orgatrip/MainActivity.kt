package com.example.orgatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.orgatrip.data.Trip
import com.example.orgatrip.presentation.trip.TripViewModel
import com.example.orgatrip.ui.theme.OrgaTripTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrgaTripTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: TripViewModel = hiltViewModel()
                    val trip = viewModel.state.value.trip
                    val isLoading = viewModel.state.value.isLoading
                    trip.let {
                        Text(text = it?.id ?: "erreur")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isLoading) {
                        CircularProgressIndicator()
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Entrez un nom de voyage")
                    Spacer(modifier = Modifier.height(16.dp))
                    val tripToInsert = remember { mutableStateOf(TextFieldValue()) }
                    TextField(
                        value = tripToInsert.value,
                        onValueChange = { tripToInsert.value = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val trip1 = Trip(name = tripToInsert.value.text)
                    Button(onClick = { viewModel.insertTrip(trip1) }) {
                        Text(text = "Valider")
                    }
                }
            }
        }
    }
}

