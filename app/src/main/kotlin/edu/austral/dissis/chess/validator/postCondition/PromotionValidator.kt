package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.Position
import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.RectangularBoard
import edu.austral.dissis.chess.factory.pieceFactory.QueenInitializer
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType

class PromotionValidator : PostConditionValidator {

    override fun validate(gameState: GameState, board: Board): PostConditionResult {
        val positionsMap = board.getPiecesPositions().toMutableMap()

        /** Recorro la fila 0 y 7 del tablero y llamo a promotePawn con cada position
         *  promotePawn se va a fijar si la pieza en esa position es un peón y, si lo es, intercambia por reina
         */
        for (row in listOf(0, 7)) {
            for (column in 0 until board.getSizeX()) {
                promotePawn(Position(row, column), gameState, positionsMap)
            }
        }
        return PostConditionResult.ResultValid(RectangularBoard(board.getSizeX(), board.getSizeY(), positionsMap))
    }

    private fun promotePawn(position: Position, gameState: GameState, map: MutableMap<Position, Piece>) {
        val piece = gameState.getActualBoard().getPieceByPosition(position)
        if (isPawn(piece)) {
            /** Obtengo el color del peón para intercambiarlo por una reina de ese equipo */
            val color = piece?.getColor() ?: return
            map[position] = QueenInitializer().initialize(color, piece!!.getId())
        }
    }

    private fun isPawn(piece: Piece?): Boolean {
        return piece?.getType() == PieceType.PAWN
    }

}
