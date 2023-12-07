package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.factory.pieceFactory.QueenInitializer
import edu.austral.dissis.checkers.validator.postCondition.HasEatenValidator
import edu.austral.dissis.checkers.validator.turn.CheckersTurnValidator
import edu.austral.dissis.common.validator.postCondition.PromotionValidator
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.winCondition.ExtinctionCondition

fun createCheckersNormalGame(): GameState {
    val board = createCheckersBoard()
    return GameStateImp(
        listOf(board),
        ExtinctionCondition(),
        CheckersTurnValidator(Color.WHITE, listOf()),
        listOf(),
        listOf(HasEatenValidator(), PromotionValidator(QueenInitializer(), PieceType.PAWN))
    )
}