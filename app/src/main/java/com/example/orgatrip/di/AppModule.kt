package com.example.orgatrip.di

import com.example.orgatrip.feature_trip.data.remote.repository.TripApiKtorClientImpl
import com.example.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun ProvideHttpClient():HttpClient{
        return HttpClient()
    }

    @Provides
    @Singleton
    fun provideTripApi(): TripApiKtorClient {
        return TripApiKtorClient.create()
    }



}