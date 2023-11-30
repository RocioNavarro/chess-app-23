package edu.austral.dissis.chess.validator.gameCondition.composition

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement

/** Toma una lista de validadores y verifica que TODOS se cumplan (and logico) */
class AndValidator( private val validators : List<edu.austral.dissis.chess.validator.Validator>) :
    edu.austral.dissis.chess.validator.Validator {

    override fun validate(movement: Movement, gameState: GameState): edu.austral.dissis.chess.validator.ValidatorResponse {
        for (validator : edu.austral.dissis.chess.validator.Validator in validators) {
            /** Pattern matching para evaluar las respuestas de cada validador de la lista. */
            when (validator.validate(movement, gameState)){
                is edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid -> continue
                is edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid -> return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("Invalid movement")
            }
        }
        return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("And composition OK")
    }
}