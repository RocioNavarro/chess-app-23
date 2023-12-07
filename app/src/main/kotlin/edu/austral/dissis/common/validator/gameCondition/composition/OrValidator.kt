package edu.austral.dissis.common.validator.gameCondition.composition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

class OrValidator(private val validators: List<Validator>) :
    Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        for (validator: Validator in validators) {
            /** Pattern matching para evaluar las respuestas de cada validador de la lista. */
            when ( validator.validate(movement, gameState)) {
                /** Por ser un OR, si un validator resulte valido, ya se retorna OK */
                is ValidatorResponse.ValidatorResultValid ->  return ValidatorResponse.ValidatorResultValid("Or composition is OK")
                is ValidatorResponse.ValidatorResultInvalid ->  continue
            }

        }
        return ValidatorResponse.ValidatorResultInvalid("OR invalid movement")
    }
}
