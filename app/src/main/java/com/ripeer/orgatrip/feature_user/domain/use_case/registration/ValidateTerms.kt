package com.ripeer.orgatrip.feature_user.domain.use_case.registration

import com.ripeer.orgatrip.feature_user.domain.use_case.ValidationResult

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidationResult {
        if(!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}