package edu.austral.dissis.chess.validator.preCondition

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.validator.ValidatorResponse

class ChessTurnValidator(private val current: Color) : TurnValidator {
    override fun getTurn(): Color {
        return current
    }

    override fun nextTurn(gameState: GameState): TurnValidator {
        if (current == Color.BLACK) {
            return ChessTurnValidator(Color.WHITE)
        } else {
            return ChessTurnValidator(Color.BLACK)
        }
    }

    override fun validateTurn(movement: Movement, gameState: GameState): ValidatorResponse {
        val pieceToMove: Piece? = getPiece(movement, gameState)

        if (pieceToMove != null) {
            if (pieceToMove.getColor() == this.current) {
                return ValidatorResponse.ValidatorResultValid("Es tu turno")
            }
        }
        return ValidatorResponse.ValidatorResultInvalid("ERROR: No es tu turno")
    }

    private fun getPiece (movement: Movement, gameState: GameState): Piece? {
        return gameState.getActualBoard().getPieceByPosition(movement.from)
    }
}