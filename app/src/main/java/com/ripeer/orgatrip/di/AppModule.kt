package com.ripeer.orgatrip.di

import com.google.firebase.auth.FirebaseAuth
import com.ripeer.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.ripeer.orgatrip.feature_user.data.FirebaseSource
import com.ripeer.orgatrip.feature_user.data.Repository
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateEmail
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidatePassword
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateRepeatedPassword
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateTerms
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
    fun provideValidateEmail(): ValidateEmail {
        return ValidateEmail()
    }
    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }
    @Provides
    @Singleton
    fun provideValidateRepeatedPassword(): ValidateRepeatedPassword {
        return ValidateRepeatedPassword()
    }
    @Provides
    @Singleton
    fun provideValidateTerms(): ValidateTerms {
        return ValidateTerms()
    }

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return Repository(FirebaseSource(FirebaseAuth.getInstance()))
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

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