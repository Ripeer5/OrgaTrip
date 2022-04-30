package com.ripeer.orgatrip.feature_user.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.ripeer.orgatrip.feature_user.data.model.UserRequestDto

interface Repository {
    fun signUpUser(email: String, password: String): Task<AuthResult>
    fun signInUser(email: String, password: String): Task<AuthResult>
    fun sendForgotPassword(email: String): Task<Void>
    suspend fun saveUser(user: UserRequestDto)
}