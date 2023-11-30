package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.validator.preCondition.turnCondition.TurnValidator
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.chess.validator.winCondition.WinCondition

sealed interface GameState{

    fun getActualBoard(): Board
    fun getBoards(): List<Board>  /** "Screenshots" con historial de tableros, se guarda el nuevo cada vez que se hace un movimiento */
    fun getCurrentTurn(): Color
    fun movePiece(movement: Movement) : GameState
    fun getTurnManager() : TurnValidator
    fun getPreConditions(): List<Validator>
    fun getPostConditions() : List<PostConditionValidator>
    fun getWinCondition() : WinCondition

}