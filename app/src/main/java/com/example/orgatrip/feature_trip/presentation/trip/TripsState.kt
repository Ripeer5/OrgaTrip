package com.example.orgatrip.feature_trip.presentation.trip

import com.example.orgatrip.feature_trip.data.remote.dao.TripResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TripsState(
    val trips: List<TripResponse> = emptyList(),
    val isLoading: Boolean = false
)
