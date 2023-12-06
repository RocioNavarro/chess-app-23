package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.validator.winCondition.WinCondition

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