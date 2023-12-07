package edu.austral.dissis.checkers.factory.pieceFactory

import edu.austral.dissis.checkers.validator.EnemyBetween
import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.boardValidator.LimitedMovementValidator
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.ExactMovementValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator

class QueenInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        return Piece(id, color, PieceType.QUEEN,
            OrValidator(
                listOf(
                    /** Se mueve una posicion hacia cualquier diagonal para adelante o atras */
                    AndValidator(listOf(DiagonalMoveValidator(), LimitedMovementValidator(1), EmptyDestinationValidator())),
                    /** Come saltando una pieza en diagonal y cayendo en casillero vacio */
                    AndValidator(listOf(DiagonalMoveValidator(), ExactMovementValidator(2), EnemyBetween(), EmptyDestinationValidator())),
                )
            )
        )
    }
}