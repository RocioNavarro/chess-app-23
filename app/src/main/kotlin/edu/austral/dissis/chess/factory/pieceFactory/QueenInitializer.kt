package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.gameCondition.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.DiagonalValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.HorizontalValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.VerticalValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.common.validator.obstacle.*
import edu.austral.dissis.chess.factory.PieceInitializer

class QueenInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid, color, PieceType.QUEEN,
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