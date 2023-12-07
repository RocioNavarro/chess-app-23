package edu.austral.dissis.common.validator.postCondition

import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.chess.validator.postCondition.PostConditionResult
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType

class PromotionValidator(private val initializer: PieceInitializer, private val typeToCheck: PieceType) :
    PostConditionValidator {

    override fun validate(gameState: GameState, board: Board): PostConditionResult {
        val positionsMap = board.getPiecesPositions().toMutableMap()

        /** Recorro la fila 0 y 7 del tablero y llamo a promotePawn con cada position
         *  promotePawn se va a fijar si la pieza en esa position es un peón y, si lo es, intercambia por reina
         */
        for (row in listOf(0, 7)) {
            for (column in 0 until board.getSizeX()) {
                promote(Position(row, column), gameState, positionsMap)
            }
        }
        return PostConditionResult.ResultValid(RectangularBoard(board.getSizeX(), board.getSizeY(), positionsMap))
    }

    private fun promote(position: Position, gameState: GameState, map: MutableMap<Position, Piece>) {
        val piece = gameState.getActualBoard().getPieceByPosition(position)
        if (isPawn(piece)) {
            /** Obtengo el color del peón para intercambiarlo por una reina de ese equipo */
            val color = piece?.getColor() ?: return
            map[position] = initializer.initialize(color, piece!!.getId())
        }
    }

    private fun isPawn(piece: Piece?): Boolean {
        return piece?.getType() == typeToCheck
    }

}
