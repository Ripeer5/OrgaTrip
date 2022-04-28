package com.ripeer.orgatrip.feature_user.data.datasources.remote

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val name: String,
    val id: String
)
