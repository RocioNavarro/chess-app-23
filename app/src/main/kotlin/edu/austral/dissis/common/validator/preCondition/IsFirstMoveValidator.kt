package edu.austral.dissis.common.validator.preCondition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

class IsFirstMoveValidator : Validator {

    /** Veo si la pieza ya se movio antes */
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val board = gameState.getActualBoard()
        val fromPiece = board.getPieceByPosition(movement.from) ?: return ValidatorResponse.ValidatorResultInvalid("No hay una pieza en esta posicion")

        if (fromPiece.getMoveCounter() == 0) return ValidatorResponse.ValidatorResultValid("Es el primer movimiento")
        return ValidatorResponse.ValidatorResultInvalid("No es el primer movimiento")
    }
}