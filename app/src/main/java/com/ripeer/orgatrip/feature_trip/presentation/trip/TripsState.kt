package com.ripeer.orgatrip.feature_trip.presentation.trip

import com.ripeer.orgatrip.feature_trip.data.remote.dao.TripResponse

data class TripsState(
    val trips: List<TripResponse> = emptyList(),
    val isLoading: Boolean = false
)
