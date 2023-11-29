package edu.austral.dissis.chess.validator.gameCondition.obstacleValidator

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement

/**
 * Verifico que haciendo el movimiento la pieza siga quedando dentro del tablero
 */
class LegalPositionValidator : edu.austral.dissis.chess.validator.Validator {

    override fun validate(movement: Movement, gameState: GameState): edu.austral.dissis.chess.validator.ValidatorResponse {

        val sizeX : Int = gameState.getActualBoard().getSizeX()
        val sizeY : Int = gameState.getActualBoard().getSizeY()

        return  if (movement.to.column < sizeX && movement.to.row < sizeY
            && movement.to.column >= 0 && movement.to.row >= 0)
            edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("La pieza sigue dentro del tablero")
        else edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("ERROR: La pieza se fue fuera del tablero")

    }
}