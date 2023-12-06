package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.validator.postCondition.IsNotCheckValidator
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.chess.validator.postCondition.PromotionValidator
import edu.austral.dissis.chess.validator.preCondition.ClassicChessTurnValidator
import edu.austral.dissis.chess.validator.winCondition.CheckMateValidator

class GameFactory {
    companion object {
        fun createChessClassicGame(): GameState {
            val board = BoardFactory.createClassicChessBoard()
            return GameStateImp(
                listOf(board),
                CheckMateValidator(),
                ClassicChessTurnValidator(Color.WHITE),
                listOf(IsNotCheckValidator()),
                listOf(PromotionValidator())
            )
        }

        fun createChessCapablancaGame(): GameState {
            val board = BoardFactory.createCapablancaChessBoard()
            return GameStateImp(
                listOf(board),
                CheckMateValidator(),
                ClassicChessTurnValidator(Color.WHITE),
                listOf(),
                listOf(PromotionValidator())
            )
        }
    }
}