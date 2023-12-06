package edu.austral.dissis.chess.validator.winCondition

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.chess.validator.postCondition.CheckValidator
import edu.austral.dissis.common.validator.WinCondition

class CheckMateValidator : WinCondition {

    private val checkValidator = CheckValidator()

    override fun isWin(gameState: GameState): Boolean {
        val enemyPositions = getAllEnemyPositions(gameState)

/*
        for (position in enemyPositions) {
            //recorre todas las piezas enemigas y obtiene todos los movimientos válidos que pueden hacer
            val validMoves : List<Movement> = findAllValidMoves(position, gameState)

            for (movement in validMoves) {
                //vemos si alguno de los movimientos no deja en jaque
                if (moveIsNotCheck(movement, gameState)) {
                    return false
                }
            }
        }

 */
        return false
    }


    private fun getAllEnemyPositions(gameState: GameState) : List<Position> {
        val board : Board = gameState.getActualBoard()
        val enemyPositions = mutableListOf<Position>()
        val currentPlayer = gameState.getCurrentTurn()
        val enemy: Color = if (currentPlayer == Color.WHITE) {
            Color.BLACK
        } else {
            Color.WHITE
        }

        for(position in board.getOccupiedPositions()){
            val piece = board.getPieceByPosition(position)
            if (piece != null && piece.getColor() == enemy) {
                enemyPositions.add(position)
            }
        }
        return enemyPositions
    }

    private fun findAllValidMoves(position: Position, gameState: GameState) : List<Movement> {
        val board: Board = gameState.getActualBoard()
        val piece: Piece = board.getPieceByPosition(position) ?: throw NoSuchElementException("No está la pieza, capo")
        val validMoves = mutableListOf<Movement>()

        for (row in 0 until board.getSizeX()) {
            for (column in 0 until board.getSizeY()) {
                val positionTo = Position(row, column)
                val movement = Movement(position, positionTo)
                val validator = piece.validateMove(movement, gameState)

                if (validator is edu.austral.dissis.common.validator.ValidatorResponse.ValidatorResultValid) {
                    validMoves.add(movement)
                }
            }
        }
        return validMoves
    }

    private fun moveIsNotCheck(movement: Movement, gameState: GameState) : Boolean{
        val nextGameState : GameState = gameState.movePiece(movement)
        return !checkValidator.validate(nextGameState)
    }

}