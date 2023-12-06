package edu.austral.dissis.common.validator.gameCondition.direction

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** Verifico que se mueva de forma vertical hacia adelante cuando sense==1 y hacia atras cuando sense==-1 */
class VerticalSenseValidator (private val sense: Int) : Validator {
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        if (movement.from.column != movement.to.column) {
            return ValidatorResponse.ValidatorResultInvalid("ERROR: No es un movimiento recto")
        }

        val isValidMove = when (sense) {
            1 -> movement.from.row < movement.to.row
            -1 -> movement.from.row > movement.to.row
            else -> false
        }

        return if (isValidMove) {
            ValidatorResponse.ValidatorResultValid("Vertical sense OK")
        } else {
            ValidatorResponse.ValidatorResultInvalid("ERROR: No es un sentido válido")
        }
    }
}