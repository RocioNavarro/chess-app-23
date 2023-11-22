package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.composition.AndValidator
import edu.austral.dissis.chess.validator.composition.OrValidator
import edu.austral.dissis.chess.validator.direction.HorizontalValidator
import edu.austral.dissis.chess.validator.direction.VerticalValidator
import edu.austral.dissis.chess.validator.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.chess.validator.obstacleValidator.EmptyVerticalValidator

class RookInitializer : PieceInitializer{
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid, color, PieceType.ROOK,
            AndValidator(
                listOf(
                    BoardBoundsValidator(),

                    OrValidator(
                        listOf(

                            AndValidator(
                                listOf(
                                    HorizontalValidator(),
                                    EmptyHorizontalValidator()
                                )
                            ),

                            AndValidator(
                                listOf(
                                    VerticalValidator(),
                                    EmptyVerticalValidator()
                                )
                            ),
                            )
                    )
                )
            )
        )
    }
}