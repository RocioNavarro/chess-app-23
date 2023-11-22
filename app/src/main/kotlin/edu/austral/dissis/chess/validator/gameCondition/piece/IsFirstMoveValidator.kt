package edu.austral.dissis.chess.validator.gameCondition.piece

import edu.austral.dissis.chess.game.ClassicBoardGameState
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.ValidatorResponse

class IsFirstMoveValidator : edu.austral.dissis.chess.validator.Validator {

    /** Veo cant de movimientos de la pieza que se quiere mover */
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()
        val fromPiece = board.getPieceByPosition(movement.from) ?: return ValidatorResponse.ValidatorResultInvalid("No hay una pieza en esta posicion")

        if (fromPiece.getMoveCounter() == 0) return ValidatorResponse.ValidatorResultValid("Es el primer movimiento")
        return ValidatorResponse.ValidatorResultInvalid("No es el primer movimiento")
    }
}