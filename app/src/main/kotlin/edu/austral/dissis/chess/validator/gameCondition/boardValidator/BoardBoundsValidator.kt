package edu.austral.dissis.chess.validator.gameCondition.boardValidator

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

// Verifico que la fila y columna a la que se está queriendo mover la pieza pertenezcan al tablero
class BoardBoundsValidator : Validator {
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val x : Int = gameState.getActualBoard().getSizeX() // Cant columnas actual board
        val y : Int = gameState.getActualBoard().getSizeY() // Cant filas actual board

        return  if (movement.to.column < x && movement.to.row < y
            && movement.to.column >= 0 && movement.to.row >= 0)
            ValidatorResponse.ValidatorResultValid("OK: seguis en el tablero")
        else ValidatorResponse.ValidatorResultInvalid("ERROR: pieza fuera del tablero")

    }
}