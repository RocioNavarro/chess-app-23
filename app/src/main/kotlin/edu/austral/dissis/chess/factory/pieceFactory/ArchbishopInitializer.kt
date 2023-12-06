package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.KnightMoveValidator
import edu.austral.dissis.common.validator.preCondition.IsEnemyValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDiagonalValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator

class ArchbishopInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id,
            color,
            PieceType.ARCHBISHOP,
            AndValidator(
                listOf(
                    LegalPositionValidator(),
                    OrValidator(listOf(
                        KnightMoveValidator(),
                        AndValidator(listOf(DiagonalMoveValidator(), EmptyDiagonalValidator()))
                    )),
                    OrValidator(
                        listOf(IsEnemyValidator(), EmptyDestinationValidator())),
                    )
            )
        )
    }
}