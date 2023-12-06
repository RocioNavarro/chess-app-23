package edu.austral.dissis.chess.validator.preCondition.turnCondition

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.ValidatorResponse

class ClassicChessTurnValidator(private val current: Color) : TurnValidator {
    override fun getTurn(): Color {
        return current
    }

    override fun nextTurn(): TurnValidator {
        if (current == Color.BLACK) {
            return ClassicChessTurnValidator(Color.WHITE)
        } else {
            return ClassicChessTurnValidator(Color.BLACK)
        }
    }

    override fun validateTurn(movement: Movement, gameState: GameState): edu.austral.dissis.common.validator.ValidatorResponse {
        val pieceToMove: Piece? = getPiece(movement, gameState)

        if (pieceToMove != null) {
            if (pieceToMove.getColor() == this.current) {
                return edu.austral.dissis.common.validator.ValidatorResponse.ValidatorResultValid("Es tu turno")
            }
        }
        return edu.austral.dissis.common.validator.ValidatorResponse.ValidatorResultInvalid("ERROR: No es tu turno")
    }

    private fun getPiece (movement: Movement, gameState: GameState): Piece? {
        return gameState.getActualBoard().getPieceByPosition(movement.from)
    }
}