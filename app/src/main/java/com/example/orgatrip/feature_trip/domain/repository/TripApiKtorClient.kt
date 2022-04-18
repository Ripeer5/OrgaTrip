package com.example.orgatrip.feature_trip.domain.repository

import com.example.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.example.orgatrip.feature_trip.data.remote.dao.TripResponse
import com.example.orgatrip.feature_trip.data.remote.repository.TripApiKtorClientImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.flow.Flow

interface TripApiKtorClient {

    suspend fun getTrips(): List<TripResponse>

    suspend fun createTrip(tripRequest: TripRequest): TripResponse?

    companion object{
        fun create(): TripApiKtorClient {
            return TripApiKtorClientImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

}