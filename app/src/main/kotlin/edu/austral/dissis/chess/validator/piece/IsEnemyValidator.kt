package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.game.ClassicBoardGameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

class IsEnemyValidator : Validator {

    override fun validate(movement: Movement, gameState: ClassicBoardGameState): ValidatorResponse {
        val board = gameState.getActualBoard()

        /** Comparo colores */
        board.getPieceByPosition(movement.to)?.let {
            if (it.getColor() != gameState.getCurrentTurn())
                return ValidatorResponse.ValidatorResultValid("Es enemigo")
        }
        return ValidatorResponse.ValidatorResultInvalid("No es enemigo")
    }
}