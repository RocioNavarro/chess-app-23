package edu.austral.dissis.chess.factory.pieceInitializer

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.boardValidator.LimitedMovementValidator
import edu.austral.dissis.chess.validator.composition.AndValidator
import edu.austral.dissis.chess.validator.composition.OrValidator
import edu.austral.dissis.chess.validator.direction.DiagonalValidator
import edu.austral.dissis.chess.validator.direction.VerticalValidator
import edu.austral.dissis.chess.validator.obstacleValidator.EmptyVerticalValidator
import edu.austral.dissis.chess.validator.piece.IsEnemyValidator
import edu.austral.dissis.chess.validator.piece.IsFirstMoveValidator
import edu.austral.dissis.chess.factory.PieceInitializer

class PawnInitializer : PieceInitializer {

    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid,
            color,
            PieceType.PAWN,
            AndValidator(
                listOf(
                    BoardBoundsValidator(),
                    OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    VerticalValidator(),
                                    EmptyVerticalValidator(),
                                    LimitedMovementValidator(1)
                                )
                            ),
                            AndValidator(
                                listOf( /** Cuando es el primer movimiento */
                                    IsFirstMoveValidator(),
                                    VerticalValidator(),
                                    EmptyVerticalValidator(),
                                    LimitedMovementValidator(2)

                                )
                            ),
                            AndValidator(
                                listOf(
                                    IsEnemyValidator(),
                                    DiagonalValidator(),
                                    LimitedMovementValidator(1)
                                )
                            )
                            //TODO PromotionValidator()
                        )
                    )
                )
            )
        )
    }

}