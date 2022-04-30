package com.ripeer.orgatrip.di

import com.google.firebase.auth.FirebaseAuth
import com.ripeer.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.ripeer.orgatrip.feature_user.data.datasources.remote.FirebaseSource
import com.ripeer.orgatrip.feature_user.data.datasources.remote.MongoDbSource
import com.ripeer.orgatrip.feature_user.data.repository.RepositoryImpl
import com.ripeer.orgatrip.feature_user.domain.repository.Repository
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
import com.ripeer.orgatrip.feature_user.domain.use_case.login.LoginValidateEmail
import com.ripeer.orgatrip.feature_user.domain.use_case.login.LoginValidatePassword
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    @Singleton
    fun provideMongoDbSource(): MongoDbSource {
        return MongoDbSource(HttpClient())
    }*/

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun provideTripApi(): TripApiKtorClient {
        return TripApiKtorClient.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }

    @Provides
    @Singleton
    fun provideRepository(
        fireBaseSource: FirebaseSource,
        mongoDb: MongoDbSource
    ): Repository {
        return RepositoryImpl(fireBaseSource, mongoDb)
    }

    @Provides
    @Singleton
    fun provideFirebaseSource(firebaseAuth: FirebaseAuth): FirebaseSource {
        return FirebaseSource(firebaseAuth)
    }


    @Provides
    @Singleton
    fun provideLoginValidateEmail(): LoginValidateEmail {
        return LoginValidateEmail()
    }
    @Provides
    @Singleton
    fun provideLoginValidatePassword(): LoginValidatePassword {
        return LoginValidatePassword()
    }

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

}