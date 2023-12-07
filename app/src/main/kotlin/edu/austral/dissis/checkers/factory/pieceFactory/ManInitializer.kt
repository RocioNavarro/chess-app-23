package edu.austral.dissis.checkers.factory.pieceFactory

import edu.austral.dissis.checkers.validator.EnemyBetween
import edu.austral.dissis.common.piece.PieceInitializer
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.boardValidator.LimitedMovementValidator
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.ExactMovementValidator
import edu.austral.dissis.common.validator.gameCondition.movement.ForwardSenseValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator

/** Man es cada dama */
class ManInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        val sense = if (color == Color.WHITE) 1 else -1

        return Piece(id, color, PieceType.PAWN,
            OrValidator(
                listOf(
                    /** Se mueve 1 lugar en diagonal hacia adelante */
                    AndValidator(listOf(DiagonalMoveValidator(), LimitedMovementValidator(1), ForwardSenseValidator(sense), EmptyDestinationValidator())),
                    /** Come saltando una pieza en diagonal y cayendo en casillero vacio */
                    AndValidator(listOf(ForwardSenseValidator(sense), DiagonalMoveValidator(), ExactMovementValidator(2), EnemyBetween(), EmptyDestinationValidator()))
                )
            )
        )
    }
}