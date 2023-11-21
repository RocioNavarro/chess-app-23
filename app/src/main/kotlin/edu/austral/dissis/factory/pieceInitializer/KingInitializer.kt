package edu.austral.dissis.factory.pieceInitializer

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.PieceType
import edu.austral.dissis.chess.validator.boardValidator.BoardBoundsValidator
import edu.austral.dissis.chess.validator.boardValidator.LimitedMovementValidator
import edu.austral.dissis.chess.validator.composition.AndValidator
import edu.austral.dissis.chess.validator.composition.OrValidator
import edu.austral.dissis.chess.validator.direction.DiagonalValidator
import edu.austral.dissis.chess.validator.direction.HorizontalValidator
import edu.austral.dissis.chess.validator.direction.VerticalValidator
import edu.austral.dissis.factory.PieceInitializer

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
                                            HorizontalValidator(),
                                            VerticalValidator(),
                                            DiagonalValidator(),
                                        )
                                    ),
                                    /** Cant de casilleros que puede avanzar*/
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
}