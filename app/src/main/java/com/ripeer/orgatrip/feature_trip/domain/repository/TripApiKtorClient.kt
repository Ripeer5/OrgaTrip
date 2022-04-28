package com.ripeer.orgatrip.feature_trip.domain.repository

import com.ripeer.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.ripeer.orgatrip.feature_trip.data.remote.dao.TripResponse
import com.ripeer.orgatrip.feature_trip.data.remote.repository.TripApiKtorClientImpl
import com.ripeer.orgatrip.feature_trip.domain.model.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface TripApiKtorClient {

    suspend fun getTrips(): List<TripResponse>

    suspend fun createTrip(tripRequest: TripRequest): TripResponse?

    suspend fun saveUser(email: String, name: String, id: String)

    suspend fun fetchUser(id: String): User

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