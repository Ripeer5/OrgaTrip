package com.ripeer.orgatrip.feature_user.domain.use_case.registration

import com.ripeer.orgatrip.feature_user.domain.use_case.ValidationResult

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}