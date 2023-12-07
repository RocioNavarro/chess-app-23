package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.factory.BoardFactory.Companion.createCapablancaChessBoard
import edu.austral.dissis.chess.factory.BoardFactory.Companion.createClassicChessBoard
import edu.austral.dissis.chess.factory.pieceFactory.QueenInitializer
import edu.austral.dissis.chess.validator.postCondition.CastleLeftPostCondition
import edu.austral.dissis.chess.validator.postCondition.CastleRightPostCondition
import edu.austral.dissis.chess.validator.preCondition.IsNotCheckValidator
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.chess.validator.postCondition.PromotionValidator
import edu.austral.dissis.chess.validator.preCondition.ChessTurnValidator
import edu.austral.dissis.chess.validator.winCondition.CheckMateValidator
import edu.austral.dissis.common.piece.PieceType

fun createChessNormalGame(): GameState {
    val board = createClassicChessBoard()
    return GameStateImp(
        listOf(board),
        CheckMateValidator(),
        ChessTurnValidator(Color.WHITE),
        listOf(IsNotCheckValidator()),
        listOf(PromotionValidator(QueenInitializer(), PieceType.PAWN), CastleRightPostCondition(), CastleLeftPostCondition())
    )
}

fun createChessCapablancaGame(): GameState {
    val board = createCapablancaChessBoard()
    return GameStateImp(
        listOf(board),
        CheckMateValidator(),
        ChessTurnValidator(Color.WHITE),
        listOf(IsNotCheckValidator()),
        listOf(PromotionValidator(QueenInitializer(), PieceType.PAWN))
    )
}
