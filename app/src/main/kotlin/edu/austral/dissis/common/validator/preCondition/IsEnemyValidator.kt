package edu.austral.dissis.common.validator.preCondition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

class IsEnemyValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()
        val piece = board.getPieceByPosition(movement.to)

        return if ( piece == null ) ValidatorResponse.ValidatorResultInvalid("ERROR: no hay pieza")
        /** Comparo colores */
        else {
            if (piece.getColor() != gameState.getCurrentTurn()) ValidatorResponse.ValidatorResultValid("Es enemigo ")
            else ValidatorResponse.ValidatorResultInvalid("No es enemigo")
        }
    }
}