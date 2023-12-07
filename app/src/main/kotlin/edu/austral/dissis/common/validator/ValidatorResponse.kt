package edu.austral.dissis.common.validator

sealed interface ValidatorResponse {

    data class ValidatorResultValid(val message: String) : ValidatorResponse
    data class ValidatorResultInvalid(val message: String) : ValidatorResponse

}
