package com.example.orgatrip.data

import retrofit2.http.GET
import retrofit2.http.Path

interface TripAPI {

    @GET("/trip")
    suspend fun getTrip(): Trip

    @GET("/trip/{id}")
    suspend fun getTripById(@Path("id") tripId:String): Trip

    companion object {
        const val BASE_URL = "http://192.168.100.65:8080"
    }

}