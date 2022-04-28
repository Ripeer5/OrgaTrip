package com.ripeer.orgatrip.feature_user.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ripeer.orgatrip.feature_user.data.datasources.remote.UserRequestDto
import com.ripeer.orgatrip.feature_user.data.repository.RepositoryImpl
import com.ripeer.orgatrip.feature_user.domain.repository.Repository
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateEmail
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidatePassword
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateRepeatedPassword
import com.ripeer.orgatrip.feature_user.domain.use_case.registration.ValidateTerms
import com.ripeer.orgatrip.feature_user.presentation.ValidationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val repository: Repository,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    /*init {
        auth.signOut()
        val currentUser = auth.currentUser
        signedIn.value = currentUser !=null
        currentUser?.uid?.let { uid ->
            getUserData(uid)
        }
    }*/

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword
        )
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        repository.signUpUser(state.email, state.password).addOnSuccessListener {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
                repository.saveUser(UserRequestDto("test", "testEmail", "testId"))

            }
        }

    }
}