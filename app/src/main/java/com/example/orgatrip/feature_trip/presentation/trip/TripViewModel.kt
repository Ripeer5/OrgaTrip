package com.example.orgatrip.feature_trip.presentation.trip

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.example.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val api: TripApiKtorClient
) : ViewModel() {

    private var getTripsJob: Job? = null

    private val _tripsState = mutableStateOf(TripsState())
    val tripsState: State<TripsState> = _tripsState

    init {
        getTrips()
    }

    fun getTrips() {

        /*viewModelScope.launch {
            api.getTrips().onEach { trips ->
                _tripsState.value = tripsState.value.copy(
                    trips = trips
                )
            }*/
        viewModelScope.launch {
            try {
                _tripsState.value = tripsState.value.copy(
                    isLoading = true
                )
                _tripsState.value = tripsState.value.copy(
                    trips = api.getTrips(),
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("MainViewModel", "getTrip: ", e)
                _tripsState.value = tripsState.value.copy(
                    isLoading = false
                )
            }
        }
        /*_tripsState.value = tripsState.value.copy(
            trips = service.getTrips()
        )*/
    }


    //gestion des event
    fun onEvent() {

    }


fun createTrip(trip: TripRequest) {
    viewModelScope.launch {
        api.createTrip(trip)
    }
}
}





