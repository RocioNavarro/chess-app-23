package edu.austral.dissis.common.validator.gameCondition.composition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** Toma una lista de validadores y verifica que TODOS se cumplan (and logico) */
class AndValidator( private val validators : List<Validator>) :
    Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        for (validator : Validator in validators) {
            /** Pattern matching para evaluar las respuestas de cada validador de la lista. */
            when (validator.validate(movement, gameState)){
                is ValidatorResponse.ValidatorResultValid -> continue
                is ValidatorResponse.ValidatorResultInvalid -> return ValidatorResponse.ValidatorResultInvalid("Invalid movement")
                else -> {return ValidatorResponse.ValidatorResultInvalid("Tengo seal interface y me pide un else") }
            }
        }
        return ValidatorResponse.ValidatorResultValid("And composition OK")
    }
}