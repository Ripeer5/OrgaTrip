package com.ripeer.orgatrip.feature_user.data.repository

import com.ripeer.orgatrip.feature_user.data.datasources.remote.FirebaseSource
import com.ripeer.orgatrip.feature_user.data.datasources.remote.MongoDbSource
import com.ripeer.orgatrip.feature_user.data.datasources.remote.UserRequestDto
import com.ripeer.orgatrip.feature_user.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fireBaseSource: FirebaseSource,
    private val mongoDb: MongoDbSource
) : Repository {

    override fun signUpUser(email: String, password: String) =
        fireBaseSource.signUpUser(email, password)

    override fun signInUser(email: String, password: String) =
        fireBaseSource.signInUser(email, password)

    override fun sendForgotPassword(email: String) = fireBaseSource.sendForgotPassword(email)

    override suspend fun saveUser(user: UserRequestDto) {
        mongoDb.saveUser(user)
    }

}