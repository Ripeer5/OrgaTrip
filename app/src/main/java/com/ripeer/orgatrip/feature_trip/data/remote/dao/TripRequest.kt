package com.ripeer.orgatrip.feature_trip.data.remote.dao

import kotlinx.serialization.Serializable

@Serializable
data class TripRequest(
    val name: String
)
