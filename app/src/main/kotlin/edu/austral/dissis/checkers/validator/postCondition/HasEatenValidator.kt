package edu.austral.dissis.checkers.validator.postCondition

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.validator.postCondition.PostConditionResult
import edu.austral.dissis.common.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState

/** Agarro el board como estaba antes de mover la pieza y me fijo si el ultimo movimiento comio una pieza */
class HasEatenValidator : PostConditionValidator {

    override fun validate(gameState: GameState, updatedBoard: Board): PostConditionResult {
        val previousBoard = gameState.getActualBoard()
        val latestMovement = getLatestMovement(previousBoard, updatedBoard)

        if ( !isEatingMovement(latestMovement) ) {
            return PostConditionResult.ResultInvalid("No se ha comido ninguna pieza")
        }
        val board = updateEatenBoard(updatedBoard, latestMovement)

        return PostConditionResult.ResultValid(board)
    }

    private fun getLatestMovement(previousBoard: Board, updatedBoard: Board): Movement {
        val previousBoardPieces = previousBoard.getOccupiedPositions()
        val updatedBoardPieces = updatedBoard.getOccupiedPositions() /** Piezas post movimiento */

        val positionTo = updatedBoardPieces.first { !previousBoardPieces.contains(it) }
        val piece = updatedBoard.getPieceByPosition(positionTo)

        val positionFrom = previousBoard.getPositionByPiece(piece!!)
        return Movement(positionFrom!!, positionTo)
    }

    private fun updateEatenBoard(updatedBoard: Board, latestMovement: Movement): Board {
        val positionToRemove = getEatenPosition(latestMovement)
        return updatedBoard.removePieceByPosition(positionToRemove)
    }

    private fun getEatenPosition(latestMovement: Movement): Position {
        val row = latestMovement.from.row + getRowSense(latestMovement)
        val column = latestMovement.from.column + getColumnSense(latestMovement)
        return Position(row, column)
    }


    private fun getRowSense(latestMovement: Movement): Int {
        return when {
            latestMovement.from.row < latestMovement.to.row -> 1
            latestMovement.from.row > latestMovement.to.row -> -1
            else -> 0
        }
    }

    private fun getColumnSense(latestMovement: Movement): Int {
        return when {
            latestMovement.from.column < latestMovement.to.column -> 1
            latestMovement.from.column > latestMovement.to.column -> -1
            else -> 0
        }
    }

    /** Para verificar si comio una pieza veo que se haya movido dos lugares porque solo lo puede hacer cuando salta una pieza y la come*/
    private fun isEatingMovement(latestMovement: Movement): Boolean {
        return kotlin.math.abs(latestMovement.from.row - latestMovement.to.row) == 2
    }

}