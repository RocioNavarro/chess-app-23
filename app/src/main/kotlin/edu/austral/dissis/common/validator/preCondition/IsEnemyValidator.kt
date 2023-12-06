package edu.austral.dissis.common.validator.preCondition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

class IsEnemyValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()
        val piece = board.getPieceByPosition(movement.to)

        val pieceFrom = board.getPieceByPosition(movement.from)
            ?: return ValidatorResponse.ValidatorResultInvalid("No hay pieza")

        return if ( piece == null ) ValidatorResponse.ValidatorResultInvalid("No hay pieza")
        else {
            if (piece.getColor() != pieceFrom.getColor()) ValidatorResponse.ValidatorResultValid("La pieza en destino es enemiga ")
            else ValidatorResponse.ValidatorResultInvalid("Pieza aliada en la posicion de destino")
        }
    }
}