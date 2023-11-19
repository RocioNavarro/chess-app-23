package edu.austral.dissis.chess.validator

sealed interface ValidatorResponse {

    data class ValidatorResultValid(val message: String) : ValidatorResponse

    data class ValidatorResultInvalid(val message: String) : ValidatorResponse

}
