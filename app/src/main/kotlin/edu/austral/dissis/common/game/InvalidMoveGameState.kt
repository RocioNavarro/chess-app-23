package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.winCondition.WinCondition

class InvalidMoveGameState (private val gameState: GameState,
                            val errorMessage: String) : GameState {
    override fun getBoards(): List<Board> {
        return gameState.getBoards()
    }

    override fun getActualBoard(): Board {
        return gameState.getActualBoard()
    }

    override fun getCurrentTurn(): Color {
        return gameState.getCurrentTurn()
    }

    override fun movePiece(movement: Movement): GameState {
        return gameState.movePiece(movement)
    }

    override fun getTurnManager(): TurnValidator {
        return gameState.getTurnManager()
    }

    override fun getPreConditions(): List<Validator> {
        return gameState.getPreConditions()
    }

    override fun getPostConditions(): List<PostConditionValidator> {
        return gameState.getPostConditions()
    }

    override fun getWinCondition(): WinCondition {
        return gameState.getWinCondition()
    }
}