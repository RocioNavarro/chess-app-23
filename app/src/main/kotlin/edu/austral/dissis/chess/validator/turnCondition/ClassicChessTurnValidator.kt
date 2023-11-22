package edu.austral.dissis.chess.validator.turnCondition

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.validator.ValidatorResponse

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