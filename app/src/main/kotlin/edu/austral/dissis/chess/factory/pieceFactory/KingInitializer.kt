package edu.austral.dissis.chess.factory.pieceFactory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.gameCondition.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.gameCondition.boardValidator.LimitedMovementValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.chess.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.DiagonalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.HorizontalMoveValidator
import edu.austral.dissis.chess.validator.gameCondition.direction.VerticalMoveValidator
import edu.austral.dissis.chess.factory.PieceInitializer

/** Caballo */
class KingInitializer : PieceInitializer {
    override fun initialize(color: Color): Piece {
        val uuid = java.util.UUID.randomUUID().toString()
        return Piece(uuid, color, PieceType.KING,  /** Se asigna color y tipo de pieza */
            AndValidator(
                listOf(
                    BoardBoundsValidator(),
                    OrValidator(
                        listOf(
                            AndValidator(
                                listOf(
                                    /** Movimientos permitidos */
                                    OrValidator(
                                        listOf(
                                            HorizontalMoveValidator(),
                                            VerticalMoveValidator(),
                                            DiagonalMoveValidator(),
                                        )
                                    ),
                                    /** Cant de casilleros que puede avanzar */
                                    LimitedMovementValidator(1)
                                )
                            )
                            //TODO -> enroque validator
                        )
                    )
                )
            )
        )
    }

    override fun initialize(color: Color, id: String): Piece {
        TODO("Not yet implemented")
    }
}