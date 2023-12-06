package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.gameCondition.boardValidator.LimitedMovementValidator
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalMoveValidator
import edu.austral.dissis.chess.factory.PieceInitializer
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalSenseValidator
import edu.austral.dissis.common.validator.preCondition.IsEnemyValidator
import edu.austral.dissis.common.validator.preCondition.IsFirstMoveValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyDestinationValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator

class PawnInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return initialize(color, uuid)
    }

    override fun initialize(color: Color, id: String): Piece {

        val sense = if (color == Color.WHITE) 1 else -1 /** Las piezas blancas se encuentran abajo y se mueven hacia arriba y viceversa */

        return Piece(id, color, PieceType.PAWN,
            AndValidator(
                listOf(
                    LegalPositionValidator(),
                    OrValidator(
                        listOf(
                            AndValidator( /** Primer movimiento */
                                listOf(IsFirstMoveValidator(), VerticalMoveValidator(), EmptyVerticalValidator(),
                                       LimitedMovementValidator(2), EmptyDestinationValidator(), VerticalSenseValidator(sense))),
                            AndValidator( /** Despues del primer movimiento */
                                listOf(VerticalMoveValidator(), EmptyVerticalValidator(), LimitedMovementValidator(1),
                                       EmptyDestinationValidator(), VerticalSenseValidator(sense))),
                            AndValidator( /** Para comer otra pieza */
                                listOf(IsEnemyValidator(), DiagonalMoveValidator(), LimitedMovementValidator(1))
                            )
                        )
                    )
                )
            )
        )
    }
}