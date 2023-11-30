package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.game.GameStateImp
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.validator.postCondition.PromotionValidator
import edu.austral.dissis.chess.validator.preCondition.turnCondition.ClassicChessTurnValidator
import edu.austral.dissis.chess.validator.winCondition.CheckMateValidator

class GameFactory {
    companion object {
        fun createChessClassicGame(): GameState {
            val board = BoardFactory.createClassicChessBoard()
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