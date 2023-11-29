package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.chess.validator.turnCondition.TurnValidator
import edu.austral.dissis.chess.validator.winCondition.WinCondition

class InvalidMoveGameState (private val boards : List<Board>,
                            private val winCondition: WinCondition,
                            private val turnManager: TurnValidator,
                            private val preConditions: List<Validator>,
                            private val postConditions: List<PostConditionValidator>,
                            val errorMessage: String ) : GameState {
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
        TODO("Not yet implemented")
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