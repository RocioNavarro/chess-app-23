package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.game.ClassicBoardGameState
import edu.austral.dissis.chess.movement.Movement

class IsFirstMoveValidator : edu.austral.dissis.chess.validator.Validator {

    /** Veo cant de movimientos de la pieza que se quiere mover */
    override fun validate(movement: Movement, gameState: ClassicBoardGameState): edu.austral.dissis.chess.validator.ValidatorResponse {
        val board = gameState.getActualBoard()
        val fromPiece = board.getPieceByPosition(movement.from) ?: return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("No hay una pieza en esta posicion")

        if (fromPiece.getMoveCounter() == 0) return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("Es el primer movimiento")
        return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("No es el primer movimiento")
    }

}