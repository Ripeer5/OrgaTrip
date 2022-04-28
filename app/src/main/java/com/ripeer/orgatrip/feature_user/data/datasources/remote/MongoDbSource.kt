package com.ripeer.orgatrip.feature_user.data.datasources.remote

import com.ripeer.orgatrip.core.util.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class MongoDbSource @Inject constructor(
    private val client: HttpClient
) {
    suspend fun saveUser(user: UserRequestDto) {

        client.post<UserResponseDto> (HttpRoutes.USERS){
            body = user
            contentType(ContentType.Application.Json)

        }
    }
}