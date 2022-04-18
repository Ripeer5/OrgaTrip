package com.example.orgatrip.feature_trip.data.remote.repository

import com.example.orgatrip.feature_trip.data.remote.HttpRoutes
import com.example.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.example.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.example.orgatrip.feature_trip.data.remote.dao.TripResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class TripApiKtorClientImpl @Inject constructor(
    private val client: HttpClient
) : TripApiKtorClient {

    override suspend fun getTrips(): List<TripResponse> {

        return try {
                client.get {
                    url(HttpRoutes.TRIPS)
                }


        } catch (e: RedirectResponseException) {
            //3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            //4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            //3 xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createTrip(tripRequest: TripRequest): TripResponse? {

        return try {
            client.post<TripResponse> {
                url(HttpRoutes.TRIPS)
                contentType(ContentType.Application.Json)
                body = tripRequest
            }

        } catch (e: RedirectResponseException) {
            //3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            //4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            //3 xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}