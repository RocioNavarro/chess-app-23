package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.direction.KnightMoveValidator
import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.preCondition.IsEnemyValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator

/** Caballo */
class KnightInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {

        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id, color, PieceType.KNIGHT,
            AndValidator(
                listOf( KnightMoveValidator(), LegalPositionValidator(),
                    OrValidator(listOf(IsEnemyValidator(), EmptyDestinationValidator()))
                )
            )
        )
    }
}