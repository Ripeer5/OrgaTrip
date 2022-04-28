package com.ripeer.orgatrip.feature_user.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripeer.orgatrip.feature_user.data.repository.RepositoryImpl
import com.ripeer.orgatrip.feature_user.domain.use_case.login.LoginValidateEmail
import com.ripeer.orgatrip.feature_user.domain.use_case.login.LoginValidatePassword
import com.ripeer.orgatrip.feature_user.presentation.ValidationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: RepositoryImpl,
    private val validateEmail: LoginValidateEmail,
    private val validatePassword: LoginValidatePassword
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        repository.signInUser(state.email, state.password).addOnSuccessListener {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }.addOnCompleteListener {
            println(it.exception)
        }

    }


}