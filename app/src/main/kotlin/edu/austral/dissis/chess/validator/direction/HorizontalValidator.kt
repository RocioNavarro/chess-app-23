package edu.austral.dissis.chess.validator.direction

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

class HorizontalValidator: Validator {

    override fun validate(movement: Movement, gameState: GameState) : ValidatorResponse {
        val fromX = movement.from.row
        val fromY = movement.from.column
        val toX = movement.to.row
        val toY = movement.to.column

        return isHorizontalMove(fromX, fromY, toX, toY)
    }

    private fun isHorizontalMove(fromX: Int, fromY: Int, toX: Int, toY: Int): ValidatorResponse {
        val deltaX = toX - fromX
        val deltaY = toY - fromY

        // El movimiento es horizontal si hubo cambio en direccion Y pero no en X
        return  if (deltaX == 0 && deltaY != 0) ValidatorResponse.ValidatorResultValid("Movimiento OK")
        else ValidatorResponse.ValidatorResultInvalid("ERROR: No es un movimiento horizontal.")
    }
}