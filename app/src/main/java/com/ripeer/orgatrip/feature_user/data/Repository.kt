package com.ripeer.orgatrip.feature_user.data

import javax.inject.Inject

class Repository @Inject constructor(private val fireBaseSource: FirebaseSource){

    fun signUpUser(email: String, password: String) = fireBaseSource.signUpUser(email, password)

    fun signInUser(email: String, password: String) = fireBaseSource.signInUser(email, password)

    fun sendForgotPassword(email: String)=fireBaseSource.sendForgotPassword(email)
}