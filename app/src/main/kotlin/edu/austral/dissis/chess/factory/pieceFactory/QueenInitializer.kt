package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.DiagonalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.HorizontalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.VerticalMoveValidator
import edu.austral.dissis.chess.validator.preCondition.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.chess.validator.preCondition.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.common.validator.obstacle.*
import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.chess.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.chess.validator.preCondition.obstacleValidator.LegalPositionValidator
import edu.austral.dissis.chess.validator.preCondition.IsEnemyValidator

class QueenInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id,
            color,
            PieceType.QUEEN,
            AndValidator(
                listOf(
                    LegalPositionValidator(),
                    OrValidator(
                        listOf(
                            IsEnemyValidator(),
                            EmptyDestinationValidator()
                        )
                    ),
                    OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    HorizontalMoveValidator(),
                                    EmptyHorizontalValidator()
                                )
                            ),
                            AndValidator(
                                listOf(
                                    VerticalMoveValidator(),
                                    EmptyVerticalValidator()
                                )
                            ),
                            AndValidator(
                                listOf(
                                    DiagonalMoveValidator(),
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