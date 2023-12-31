package edu.austral.dissis.common.validator.gameCondition.movement

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse
import kotlin.math.abs

class DiagonalMoveValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val fromX = movement.from.row
        val fromY = movement.from.column
        val toX = movement.to.row
        val toY = movement.to.column

        return isDiagonalMove(fromX, fromY, toX, toY)
    }

    private fun isDiagonalMove(fromX: Int, fromY: Int, toX: Int, toY: Int): ValidatorResponse {
        val deltaX = toX - fromX
        val deltaY = toY - fromY

        /** Un movimiento es diagonal si el cambio en X es igual al cambio en Y */
        return  if (abs(deltaX) == abs(deltaY)) ValidatorResponse.ValidatorResultValid("Movimiento OK")
        else ValidatorResponse.ValidatorResultInvalid("ERROR: El movimiento no es diagonal.")
    }

}