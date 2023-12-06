package edu.austral.dissis.common.validator.gameCondition.movement

import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** Para setear cant de casilleros que puede moverse una pieza */
class ExactMovementValidator(private val distance: Int) : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val horizontalDistance = kotlin.math.abs(movement.from.column - movement.to.column)
        val verticalDistance = kotlin.math.abs(movement.from.row - movement.to.row)
        return if (horizontalDistance == distance || verticalDistance == distance) {
            ValidatorResponse.ValidatorResultValid("Valid")
        } else {
            ValidatorResponse.ValidatorResultInvalid("Invalid")
        }
    }

}