package edu.austral.dissis.factory.pieceInitializer

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.direction.KnightValidator
import edu.austral.dissis.factory.PieceInitializer

class KnightInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid, color, PieceType.KNIGHT, KnightValidator())
    }
}