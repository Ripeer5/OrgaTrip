package com.example.orgatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.example.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.example.orgatrip.feature_trip.data.remote.dao.TripResponse
import com.example.orgatrip.feature_trip.data.remote.repository.TripApiKtorClientImpl
import com.example.orgatrip.feature_trip.domain.model.Trip
import com.example.orgatrip.feature_trip.presentation.trip.TripViewModel
import com.example.orgatrip.feature_trip.presentation.trip.TripsScreen
import com.example.orgatrip.feature_trip.presentation.util.Screen
import com.example.orgatrip.ui.theme.OrgaTripTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //use DI !!!!
    private val service = TripApiKtorClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: TripViewModel = hiltViewModel()

            val trips = viewModel.tripsState.value.trips
            val tripToInsert = remember { mutableStateOf(TextFieldValue()) }
            OrgaTripTheme {

                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TripScreen.route
                    ){
                        composable(route = Screen.TripScreen.route) {
                            TripsScreen(navController = navController)
                        }
                    }
                }



                /*Column {
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

                *//*Column(
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
                }*/
            }

        }
    }
}


