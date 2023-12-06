package edu.austral.dissis.chess.validator.winCondition

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.chess.validator.preCondition.CheckValidator
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.validator.ValidatorResponse
import edu.austral.dissis.common.validator.winCondition.WinCondition

class CheckMateValidator : WinCondition {

    override fun isWin(gameState: GameState): Boolean {
        val kingColor = gameState.getTurnManager().nextTurn().getTurn() //el color del rey es el del próximo turno, que seria el enemigo
        val board = gameState.getActualBoard()
        val positionsOfAlliedKingPieces = getPositionsByColor(board, kingColor)
        val possibleMoves = getPossibleMoves(positionsOfAlliedKingPieces, gameState)
        for(pos in possibleMoves) {
            if (moveIsNotCheck(pos, gameState, kingColor)) {
                return false
            }
        }
        return true
    }

    private fun getPositionsByColor(board: Board, color: Color) : List<Position> {
        val occupiedPositions = mutableListOf<Position>()

        for(position in board.getOccupiedPositions()){
            val piece = board.getPieceByPosition(position)
            if (piece != null && piece.getColor() == color) {
                occupiedPositions.add(position)
            }
        }
        return occupiedPositions
    }

    private fun getPossibleMoves(positions: List<Position>, gameState: GameState) : List<Movement> {
        val possibleMoves = mutableListOf<Movement>()
        for(pos in positions) {
            possibleMoves.addAll(getPieceValidMoves(pos, gameState))
        }
        return possibleMoves
    }

    private fun getPieceValidMoves(occupiedPosition: Position, gameState: GameState) : List<Movement> {
        val piece = gameState.getActualBoard().getPieceByPosition(occupiedPosition) ?: throw NoSuchElementException("No estÃ¡ la pieza, capo")
        val validMoves = mutableListOf<Movement>()
        for (row in 0 until gameState.getActualBoard().getSizeX()) {
            for (column in 0 until gameState.getActualBoard().getSizeY()) {
                val positionTo = Position(row, column)
                val movement = Movement(occupiedPosition, positionTo)
                val validator = piece.validateMove(movement, gameState)
                if (validator is ValidatorResponse.ValidatorResultValid) {
                    validMoves.add(movement)
                }
            }
        }
        return validMoves
    }

    private fun moveIsNotCheck(movement: Movement, gameState: GameState, kingColor: Color): Boolean {
        val newGameState = simulateMove(movement, gameState) //simulo el movimiento
        return !CheckValidator().validateCheck(newGameState, kingColor) //me fijo si me deja al actual turno en check
    }

    private fun simulateMove(movement: Movement, gameState: GameState): GameState {
        val newBoard = gameState.getActualBoard().update(movement)
        val newBoards = gameState.getBoards().toMutableList()
        newBoards.add(newBoard)
        return GameStateImp(
            newBoards,
            gameState.getWinCondition(),
            gameState.getTurnManager(),
            gameState.getPreConditions(),
            gameState.getPostConditions()
        )
    }

}