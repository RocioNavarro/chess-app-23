package edu.austral.dissis.factory.pieceInitializer

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.composition.AndValidator
import edu.austral.dissis.chess.validator.composition.OrValidator
import edu.austral.dissis.chess.validator.direction.DiagonalValidator
import edu.austral.dissis.chess.validator.direction.HorizontalValidator
import edu.austral.dissis.chess.validator.direction.VerticalValidator
import edu.austral.dissis.chess.validator.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.chess.validator.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.common.validator.obstacle.*
import edu.austral.dissis.factory.PieceInitializer

class QueenInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid,
            color,
            PieceType.QUEEN,
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
                            AndValidator(
                                listOf(
                                    DiagonalValidator(),
                                    EmptyDiagonalValidator()
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}