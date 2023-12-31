package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.chess.validator.movement.CastleLeftValidator
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse
import edu.austral.dissis.common.validator.postCondition.PostConditionResult
import edu.austral.dissis.common.validator.postCondition.PostConditionValidator

/** Una vez que se valido que se puede hacer el ENROQUE LARGO, aca hago el movimiento de ambas piezas para intercambiar*/
class CastleLeftPostCondition : PostConditionValidator {

    private val castleLeftValidator: Validator = CastleLeftValidator()
    override fun validate(gameState: GameState, updatedBoard: Board): PostConditionResult {
        val movement = findMovement(gameState, updatedBoard)
        if (castleLeftValidator.validate(movement, gameState) is ValidatorResponse.ValidatorResultInvalid) return PostConditionResult.ResultInvalid("No se puede realizar el enroque")
        val newBoard = updateRookPosition(updatedBoard, movement)
        return PostConditionResult.ResultValid(newBoard)
    }

    private fun updateRookPosition(board: Board, movement: Movement): Board {
        val rook = board.getPieceByPosition(Position(movement.from.row,movement.from.column - 4))!!
        return board.updatePieceByPosition(Position(movement.from.row,movement.from.column - 1), rook).removePieceByPosition(Position(movement.from.row,movement.from.column - 4))
    }

    private fun findMovement(gameState: GameState, updatedBoard: Board): Movement {
        val initialBoard = gameState.getActualBoard()
        val initialKingPosition = findKingPosition(initialBoard, gameState.getCurrentTurn())
        val updatedKingPosition = findKingPosition(updatedBoard, gameState.getCurrentTurn())
        return Movement(initialKingPosition, updatedKingPosition)
    }

    private fun findKingPosition(board: Board, color: Color): Position {
        val king = board.getPieces().find { it.getColor() == color && it.getType() == PieceType.KING }!!
        return board.getPositionByPiece(king)!!
    }
}