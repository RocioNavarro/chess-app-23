package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.gameCondition.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.HorizontalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.VerticalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.chess.validator.gameCondition.obstacleValidator.LegalPositionValidator
import edu.austral.dissis.chess.validator.gameCondition.piece.IsEnemyValidator

class RookInitializer : PieceInitializer{
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id,
            color,
            PieceType.ROOK,
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
                            )
                    )
                )
            )
        )
    }
}