package edu.austral.dissis.checkers.validator.turn

import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.preCondition.TurnValidator
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.ValidatorResponse

/** todo cambiar */
class CheckersTurnManager(private val color: Color) : TurnValidator {
    override fun getTurn(): Color {
        return color
    }

    override fun nextTurn(): TurnValidator {
        return if (color == Color.WHITE) CheckersTurnManager(Color.BLACK) else CheckersTurnManager(Color.WHITE)
    }

    override fun validateTurn(movement: Movement, gameState: GameState): ValidatorResponse {
        val pieceToMove: Piece? = getPiece(movement, gameState)

        if (pieceToMove != null) {
            if (pieceToMove.getColor() == this.color) {
                return ValidatorResponse.ValidatorResultValid("Es tu turno")
            }
        }
        return ValidatorResponse.ValidatorResultInvalid("No es tu turno capo")
    }

    private fun getPiece (movement: Movement, gameState: GameState): Piece? {
        return gameState.getActualBoard().getPieceByPosition(movement.from)
    }


}
