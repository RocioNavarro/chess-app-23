package edu.austral.dissis.chess.validator.preCondition

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

    fun validateCheck(gameState: GameState, kingColor: Color): Boolean {

        val actualBoard: Board = gameState.getActualBoard()
        val kingPosition: Position = getKingPosition(actualBoard, kingColor)?: return true

        /** Recorro todas las piezas enemigas para ver si alguna puede atacar al rey desde su position */
        for(position in getPositionsByDifferentColor(actualBoard,kingColor)) {
            if (pieceAttacksKing(gameState, position, kingPosition)) {
                return true
            }
        }
        return false
    }

    private fun getPositionsByDifferentColor(board: Board, color: Color) : List<Position> {
        val list : MutableList<Position> = mutableListOf()
        board.getOccupiedPositions().forEach{
            position ->
            val piece = board.getPieceByPosition(position)
            if (piece != null) {
                if(piece.getColor() != color)
                    list.add(position)
            }
        }
        return list
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
                                 kingPosition: Position
    ): Boolean {
        /** Veo si la pieza en position es enemiga */
            val piece : Piece = gameState.getActualBoard().getPieceByPosition(position) ?: throw NoSuchElementException("ERROR: No hay pieza en esa posicion")
            when (
                /** Veo si la pieza en position puede moverse a la kingPosition */
                piece.validateMove(Movement(position, kingPosition), gameState  )
            ) {
                is ValidatorResponse.ValidatorResultValid -> return true
                is ValidatorResponse.ValidatorResultInvalid -> return false
            }
    }



}