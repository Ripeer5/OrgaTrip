package com.ripeer.orgatrip.feature_user.data.datasources.remote

import com.ripeer.orgatrip.core.util.HttpRoutes
import com.ripeer.orgatrip.feature_user.data.model.UserRequestDto
import com.ripeer.orgatrip.feature_user.data.model.UserResponseDto
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MongoDbSource @Inject constructor(
    /*private val client: HttpClient*/
) {
    suspend fun saveUser(user: UserRequestDto) {

        val client = HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }

        client.post<UserResponseDto> (HttpRoutes.USERS){
            contentType(type = ContentType.Application.Json)
            body = user


        }
    }
}