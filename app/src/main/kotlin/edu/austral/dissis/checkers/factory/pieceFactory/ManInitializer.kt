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
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalSenseValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator

class ManInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {
        val sense = if (color == Color.WHITE) 1 else -1

        return Piece(id,
            color,
            PieceType.PAWN,
            OrValidator(
                listOf(
                    /** movimiento diagonal */
                    AndValidator(listOf(
                        DiagonalMoveValidator(),
                        LimitedMovementValidator(1),
                        VerticalSenseValidator(sense),
                        EmptyDestinationValidator()
                    )),

                    /** comer la pieza que salta en diagonal */
                    AndValidator(listOf(
                        VerticalSenseValidator(sense),
                        DiagonalMoveValidator(),
                        ExactMovementValidator(2),
                        EnemyBetween(),
                        EmptyDestinationValidator()
                    ))


                )
            )
        )
    }

}