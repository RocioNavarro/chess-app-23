package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.boardValidator.LimitedMovementValidator
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.HorizontalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalMoveValidator
import edu.austral.dissis.common.piece.PieceInitializer
import edu.austral.dissis.chess.validator.movement.CastleLeftValidator
import edu.austral.dissis.chess.validator.movement.CastleRightValidator
import edu.austral.dissis.common.validator.preCondition.IsEnemyValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator

class KingInitializer : PieceInitializer {
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id, color, PieceType.KING,
            AndValidator(
                listOf(
                    LegalPositionValidator(),
                    OrValidator(listOf(IsEnemyValidator(), EmptyDestinationValidator())),
                    OrValidator(listOf(AndValidator(
                                    listOf(LimitedMovementValidator(1),
                                           OrValidator(listOf(VerticalMoveValidator(), DiagonalMoveValidator(), HorizontalMoveValidator(),)))),
                                CastleRightValidator(),
                                CastleLeftValidator())
                    ),
                )
            )
        )
    }
}