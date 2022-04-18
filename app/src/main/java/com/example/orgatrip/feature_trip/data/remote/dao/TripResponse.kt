package com.example.orgatrip.feature_trip.data.remote.dao

import kotlinx.serialization.Serializable

@Serializable
data class TripResponse(
    val id: String,
    val name: String

)
