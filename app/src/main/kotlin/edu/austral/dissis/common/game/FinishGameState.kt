package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.validator.winCondition.WinCondition

class FinishGameState (private val boards : List<Board>,
                       private val winCondition: WinCondition,
                       private val turnManager: TurnValidator,
                       private val preConditions: List<Validator>,
                       private val postConditions: List<PostConditionValidator>) : GameState {

    override fun getActualBoard(): Board {
        return boards.last()
    }

    override fun getBoards(): List<Board> {
        return boards
    }

    override fun getCurrentTurn(): Color {
        return turnManager.getTurn()
    }

    override fun movePiece(movement: Movement): GameState {
        return this //como el juego ya finalizó, no se hace otro movimiento
    }

    override fun getTurnManager(): TurnValidator {
        return turnManager
    }

    override fun getPreConditions(): List<Validator> {
        return preConditions
    }

    override fun getPostConditions(): List<PostConditionValidator> {
        return postConditions
    }

    override fun getWinCondition(): WinCondition {
        return winCondition
    }
}