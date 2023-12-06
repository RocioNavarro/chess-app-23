package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.HorizontalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalMoveValidator
import edu.austral.dissis.common.validator.preCondition.IsEnemyValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyHorizontalValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator

class RookInitializer : PieceInitializer{
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id, color, PieceType.ROOK,
            AndValidator(
                listOf(
                    LegalPositionValidator(),
                    OrValidator( listOf(IsEnemyValidator(), EmptyDestinationValidator())),
                    OrValidator(
                        listOf(
                            AndValidator(
                                listOf(HorizontalMoveValidator(), EmptyHorizontalValidator())),
                            AndValidator(
                                listOf(VerticalMoveValidator(), EmptyVerticalValidator())),
                        )
                    )
                )
            )
        )
    }
}