package edu.austral.dissis.chess.validator.gameCondition.obstacleValidator

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

class EmptyDestinationValidator : Validator {
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()

        return if (board.getPieceByPosition(movement.to) == null) ValidatorResponse.ValidatorResultValid("OK")
        else ValidatorResponse.ValidatorResultInvalid("Hay una pieza en la posicion de destino")
    }
}