package com.ripeer.orgatrip.feature_user.data.datasources.remote

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDto(
    val name: String,
    val email: String,
    val id: String
)
