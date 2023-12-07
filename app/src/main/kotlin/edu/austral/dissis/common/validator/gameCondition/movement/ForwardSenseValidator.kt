package edu.austral.dissis.common.validator.gameCondition.movement

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** Verifico que se mueva hacia adelante cuando sense==1 y hacia atras cuando sense==-1 */
class ForwardSenseValidator(private val sense: Int) : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val isValidMove = when (sense) {
            1 -> movement.from.row < movement.to.row
            -1 -> movement.from.row > movement.to.row
            else -> false
        }

        return if (isValidMove) {
            ValidatorResponse.ValidatorResultValid("OK")
        } else {
            ValidatorResponse.ValidatorResultInvalid("No es un sentido válido")
        }
    }

}