package com.ripeer.orgatrip.feature_user.domain.use_case.login

import android.util.Patterns
import com.ripeer.orgatrip.feature_user.domain.use_case.ValidationResult

class LoginValidateEmail {
    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}