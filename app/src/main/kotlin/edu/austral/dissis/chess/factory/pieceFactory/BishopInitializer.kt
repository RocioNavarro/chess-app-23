package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.composition.AndValidator
import edu.austral.dissis.chess.validator.direction.DiagonalValidator
import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.validator.obstacle.EmptyDiagonalValidator

/** Alfil */
class BishopInitializer : PieceInitializer {
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid, color, PieceType.BISHOP,
            AndValidator(
                listOf(
                    BoardBoundsValidator(),
                    DiagonalValidator(),
                    EmptyDiagonalValidator()
                )
            )
        )
    }
}