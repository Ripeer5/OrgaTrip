package com.ripeer.orgatrip.feature_trip.data.remote.repository

import com.ripeer.orgatrip.core.util.HttpRoutes
import com.ripeer.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.ripeer.orgatrip.feature_trip.data.remote.dao.TripRequest
import com.ripeer.orgatrip.feature_trip.data.remote.dao.TripResponse
import com.ripeer.orgatrip.feature_trip.domain.model.User
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
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

    override suspend fun saveUser(email: String, name: String, id: String) {
        try {
            client.post<User> {
                url(HttpRoutes.USERS)
                contentType(ContentType.Application.Json)
                body = User(id, email, name)
            }
        }catch (e: RedirectResponseException) {
            //3xx - responses
            println("Error: ${e.response.status.description}")

        } catch (e: ClientRequestException) {
            //4xx - responses
            println("Error: ${e.response.status.description}")

        } catch (e: ServerResponseException) {
            //3 xx - responses
            println("Error: ${e.response.status.description}")

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    override suspend fun fetchUser(id: String): User {
        return try {
            client.get {
                url("${HttpRoutes.USERS}/${id}")
            }
        }catch (e: RedirectResponseException) {
            //3xx - responses
            println("Error: ${e.response.status.description}")
            User("error", "error", "error")
        } catch (e: ClientRequestException) {
            //4xx - responses
            println("Error: ${e.response.status.description}")
            User("error", "error", "error")
        } catch (e: ServerResponseException) {
            //3 xx - responses
            println("Error: ${e.response.status.description}")
            User("error", "error", "error")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            User("error", "error", "error")
        }
    }
}