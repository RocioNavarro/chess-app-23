package edu.austral.dissis.chess.validator.direction

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

class KnightValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val validMoves = setOf(
            Pair(1, 2), Pair(2, 1), Pair(1, -2), Pair(2, -1),
            Pair(-1, 2), Pair(-2, 1), Pair(-1, -2), Pair(-2, -1)
        )

        val deltaRow = movement.from.row - movement.to.row
        val deltaColumn = movement.from.column - movement.to.column

        return if (validMoves.contains(Pair(deltaRow, deltaColumn))) {
            ValidatorResponse.ValidatorResultValid("Movimiento OK")
        } else {
            ValidatorResponse.ValidatorResultInvalid("ERROR: no respeta el movimiento del caballo")
        }
    }
}