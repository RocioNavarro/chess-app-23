package edu.austral.dissis.chess.validator.gameCondition.piece

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

class IsEnemyValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()
        val piece = board.getPieceByPosition(movement.to)

        return if ( piece == null ) ValidatorResponse.ValidatorResultInvalid("ERROR: no hay pieza")
        /** Comparo colores */
        else {
            if (piece.color != gameState.getCurrentTurn()) ValidatorResponse.ValidatorResultValid("Es enemigo ")
            else ValidatorResponse.ValidatorResultInvalid("No es enemigo")
        }
    }
}