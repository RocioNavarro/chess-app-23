package edu.austral.dissis.common.validator.postCondition

import edu.austral.dissis.common.piece.PieceInitializer
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType

class PromotionValidator(private val initializer: PieceInitializer, private val typeToCheck: PieceType) :
    PostConditionValidator {

    override fun validate(gameState: GameState, board: Board): PostConditionResult {

        var newBoard = board

        for(i in 0..7) {
            val pieceWhite = getPiece(Position(7,i), newBoard)
            if ( pieceWhite!= null && isPawn(pieceWhite) && isColor(pieceWhite, Color.WHITE)) {
                val newPiece = initializer.initialize(Color.WHITE, pieceWhite.getId())
                val valPiece = newBoard.getPieceByPosition(Position(7,i))
                newBoard = newBoard.updatePieceByPosition(Position(7,i), valPiece!!.copy(type = newPiece.getType(), validator = newPiece.validator))
            }
            val pieceBlack = getPiece(Position(0,i), newBoard)
            if (pieceBlack!= null && isPawn(pieceBlack) && isColor(pieceBlack, Color.BLACK)) {
                val newPiece = initializer.initialize(Color.BLACK, pieceBlack.getId())
                val valPiece = newBoard.getPieceByPosition(Position(0,i))

                newBoard = newBoard.updatePieceByPosition(Position(0,i), valPiece!!.copy(type = newPiece.getType(), validator = newPiece.validator))
            }
        }

        return PostConditionResult.ResultValid(newBoard)
    }

    private fun getPiece(position: Position, board: Board): Piece? {
        return board.getPieceByPosition(position)
    }

    private fun isPawn(piece: Piece): Boolean {
        return piece.getType() == typeToCheck
    }

    private fun isColor(piece: Piece, color: Color): Boolean {
        return piece.getColor() == color
    }

}