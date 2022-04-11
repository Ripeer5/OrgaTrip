package com.example.orgatrip.presentation.trip

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orgatrip.data.Trip
import com.example.orgatrip.data.TripAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val api: TripAPI
): ViewModel() {

    private val _state = mutableStateOf(TripState())
    val state: State<TripState> = _state



    init {
        getTripById("LondonId")

    }

    fun insertTrip(trip: Trip) {
        viewModelScope.launch {
            api.insertTrip(trip)
        }
    }

    fun getTrip(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(
                    isLoading = true
                )
                _state.value = state.value.copy(
                    trip = api.getTrip(),
                    isLoading = false
                )
            }catch (e: Exception) {
                Log.e("MainViewModel", "getTrip: ", e)
                _state.value = state.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getTripById(tripId: String) {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(
                    isLoading = true
                )
                _state.value = state.value.copy(
                    trip = api.getTripById(tripId = tripId),
                    isLoading = false
                )
            }catch (e: Exception) {
                Log.e("MainViewModel", "getTripById: ", e)
                _state.value = state.value.copy(
                    isLoading = false
                )
            }
        }
    }

    data class TripState(
        val trip: Trip? = null,
        val isLoading: Boolean = false
    )

}