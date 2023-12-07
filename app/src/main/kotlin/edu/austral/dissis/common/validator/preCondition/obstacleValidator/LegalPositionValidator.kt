package edu.austral.dissis.common.validator.preCondition.obstacleValidator

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.ValidatorResponse

/**
 * Verifico que haciendo el movimiento la pieza siga quedando dentro del tablero
 */
class LegalPositionValidator : edu.austral.dissis.common.validator.Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val sizeX : Int = gameState.getActualBoard().getSizeX()
        val sizeY : Int = gameState.getActualBoard().getSizeY()

        return  if (movement.to.column < sizeX && movement.to.row < sizeY
            && movement.to.column >= 0 && movement.to.row >= 0)
            ValidatorResponse.ValidatorResultValid("La pieza sigue dentro del tablero")
        else ValidatorResponse.ValidatorResultInvalid("ERROR: La pieza se fue fuera del tablero")

    }
}