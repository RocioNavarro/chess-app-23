package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.ValidatorResponse

/**
 *  Validador de JAQUE
 */
class CheckValidator {

    fun validate( gameState: GameState): Boolean {

        val kingColor: Color = gameState.getCurrentTurn()
        val actualBoard: Board = gameState.getActualBoard()
        val kingPosition: Position = getKingPosition(actualBoard, kingColor)?: throw NoSuchElementException("ERROR: No hay rey")
        val enemyCoordinates: List<Position> = actualBoard.getOccupiedPositions()

        /** Recorro todas las piezas enemigas para ver si alguna puede atacar al rey desde su position */
        for(position in enemyCoordinates) {
            if (pieceAttacksKing(gameState, position, kingColor, kingPosition)) {
                return true
            }
        }
        return false
    }

    private fun getKingPosition(actualBoard: Board, color: Color): Position? {
        actualBoard.getOccupiedPositions().forEach { coordinate ->
            if (actualBoard.getPieceByPosition(coordinate)?.getType() == PieceType.KING && actualBoard.getPieceByPosition(coordinate)?.getColor() == color) {
                return coordinate
            }
        }
        return null
    }

    /** Veo si una pieza ubicada en position puede atacar al rey */
    private fun pieceAttacksKing(gameState: GameState,
                                 position: Position,
                                 kingColor: Color,
                                 kingPosition: Position
    ): Boolean {
        /** Veo si la pieza en position es enemiga */
        if (gameState.getActualBoard().getPieceByPosition(position)?.getColor() != kingColor) {
            val piece : Piece = gameState.getActualBoard().getPieceByPosition(position) ?: throw NoSuchElementException("No esta la pieza capo")
            when (
                /** Veo si la pieza en position puede moverse a la kingPosition */
                piece.validateMove(Movement(position, kingPosition), gameState  )
            ) {
                is ValidatorResponse.ValidatorResultValid -> return true
                is ValidatorResponse.ValidatorResultInvalid -> return false
            }
        }
        return false
    }
}