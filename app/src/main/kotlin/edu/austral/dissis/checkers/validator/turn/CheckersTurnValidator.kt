package edu.austral.dissis.checkers.validator.turn

import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.ValidatorResponse

class CheckersTurnValidator(private val color: Color, private val posibleMoves: List<Movement>) : TurnValidator {
    override fun getTurn(): Color {
        return color
    }

    override fun nextTurn(gameState: GameState): TurnValidator {
        return if (canCapture(gameState) && hasEatenMove(gameState)) {
            nextTurnWithCapture(gameState)
        } else {
            nextTurnWithoutCapture()
        }
    }

    private fun hasEatenMove(gameState: GameState): Boolean {
        val board = gameState.getActualBoard()
        if (gameState.getBoards().size < 2) { //porque mínimo tuvo que haber movido 2 veces
            return false
        }
        val previousBoard = gameState.getBoards()[gameState.getBoards().size - 2]
        val movement = getLatestMovement(previousBoard, board)
        val previousPiece = previousBoard.getPieceByPosition(movement.from) ?: return false
        val previousColor = previousPiece.getColor()
        return previousBoard.getOccupiedPositions().size > board.getOccupiedPositions().size && previousColor == color
    }

    private fun nextTurnWithCapture(gameState: GameState): TurnValidator {
        val movement = getLatestMovement(gameState.getBoards()[gameState.getBoards().size - 2], gameState.getActualBoard())
        val newPosibleMoves = getPosibleEatenMovesByPosition(movement.to, gameState)

        return if (newPosibleMoves.isNotEmpty()) {
            CheckersTurnValidator(color, newPosibleMoves)
        } else {
            nextTurnWithoutCapture()
        }
    }

    private fun nextTurnWithoutCapture(): TurnValidator {
        return if (color == Color.WHITE) {
            CheckersTurnValidator(Color.BLACK, listOf())
        } else {
            CheckersTurnValidator(Color.WHITE, listOf())
        }
    }

    private fun canCapture(gameState: GameState): Boolean {
        val board = gameState.getActualBoard()

        if (gameState.getBoards().size < 2) { //porque mínimo tuvo que haber movido 2 veces
            return false
        }
        val previousBoard = gameState.getBoards()[gameState.getBoards().size - 2]
        val movement = getLatestMovement(previousBoard, board)
        val piece = board.getPieceByPosition(movement.to) ?: return false
        val posibleEatenMoves = getPosibleEatenMovesByPosition(movement.to, gameState)

        return posibleEatenMoves.isNotEmpty()

    }

    private fun getPosibleEatenMovesByPosition(pos: Position, gameState: GameState): List<Movement> {
        val board = gameState.getActualBoard()
        val piece = board.getPieceByPosition(pos) ?: return listOf()
        val pieceColor = piece.getColor()
        val posibleEatenMoves = mutableListOf<Movement>()
        val posibleMoves = getPosibleMovesByPosition(pos, gameState)
        for (move in posibleMoves) {
            if (isEatingMovement(move, gameState)) {
                posibleEatenMoves.add(move)
            }
        }
        return posibleEatenMoves
    }

    override fun validateTurn(movement: Movement, gameState: GameState): ValidatorResponse {
        val pieceToMove: Piece? = getPiece(movement, gameState)

        if (posibleMoves.isNotEmpty() && !posibleMoves.contains(movement)) {
            return ValidatorResponse.ValidatorResultInvalid("Con la pieza que moviste podes comer otro oponente" )
        }
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

    private fun getLatestMovement(previousBoard: Board, updatedBoard: Board): Movement {
        val previousBoardPieces = previousBoard.getOccupiedPositions()
        val updatedBoardPieces = updatedBoard.getOccupiedPositions() //piezas post movimiento

        val positionTo = updatedBoardPieces.first { !previousBoardPieces.contains(it) }
        val piece = updatedBoard.getPieceByPosition(positionTo)

        val positionFrom = previousBoard.getPositionByPiece(piece!!)
        return Movement(positionFrom!!, positionTo)
    }

    private fun isEatingMovement(move: Movement, gameState: GameState): Boolean {
        val board = gameState.getActualBoard()
        val piece = board.getPieceByPosition(move.from)!!

        return piece.validateMove(move, gameState) is ValidatorResponse.ValidatorResultValid
    }

    private fun getPosibleMovesByPosition (position: Position, gameState: GameState ): List<Movement> {
        val posibleMoves = mutableListOf<Movement>()
        val row = position.row
        val column = position.column
        val posiblePositions = listOf(Position(row + 2, column + 2), Position(row + 2, column - 2), Position(row - 2, column + 2), Position(row - 2, column - 2))
        val rows = gameState.getActualBoard().getSizeX()
        val columns = gameState.getActualBoard().getSizeY()
        val validatedPossiblePositions = posiblePositions.filter { it.row in 0..rows && it.column in 0..columns }
        for (posiblePosition in validatedPossiblePositions) {
            posibleMoves.add(Movement(position, posiblePosition))
        }
        return posibleMoves
    }
}