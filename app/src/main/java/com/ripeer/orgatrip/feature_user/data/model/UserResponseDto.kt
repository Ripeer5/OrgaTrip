package com.ripeer.orgatrip.feature_user.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val email: String,
    val name: String,
    val id: String
)
