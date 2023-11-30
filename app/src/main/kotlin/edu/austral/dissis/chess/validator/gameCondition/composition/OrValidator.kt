package edu.austral.dissis.chess.validator.gameCondition.composition

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement

class OrValidator(private val validators: List<edu.austral.dissis.chess.validator.Validator>) :
    edu.austral.dissis.chess.validator.Validator {

    override fun validate(movement: Movement, gameState: GameState): edu.austral.dissis.chess.validator.ValidatorResponse {
        for (validator: edu.austral.dissis.chess.validator.Validator in validators) {
            /** Pattern matching para evaluar las respuestas de cada validador de la lista. */
            when ( validator.validate(movement, gameState)) {
                /** Por ser un OR, si un validator resulte valido, ya se retorna OK*/
                is edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid ->  return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("Or composition is OK")
                is edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid ->  continue
            }

        }
        return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("Invalid movement")
    }
}
