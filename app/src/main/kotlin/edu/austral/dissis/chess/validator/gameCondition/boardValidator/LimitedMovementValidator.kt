package edu.austral.dissis.chess.validator.gameCondition.boardValidator

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

/** Para piezas que tienen cant de movimientos limitada */
class LimitedMovementValidator( private val maxMoveQuantity: Int ) : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val horizontalDistance = kotlin.math.abs(movement.from.column - movement.to.column)
        val verticalDistance = kotlin.math.abs(movement.from.row - movement.to.row)

        val isHorizontalValid = horizontalDistance <= maxMoveQuantity
        val isVerticalValid = verticalDistance <= maxMoveQuantity

        return if (isHorizontalValid && isVerticalValid ) {
            ValidatorResponse.ValidatorResultValid("Se puede mover esa cant de lugares")
        } else {
            ValidatorResponse.ValidatorResultInvalid("ERROR: pieza no puede moverse esa cantidad de lugares")
        }
    }

}