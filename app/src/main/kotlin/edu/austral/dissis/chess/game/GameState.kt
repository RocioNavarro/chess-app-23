package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color

interface GameState{

    fun getActualBoard(): Board
    // "Screenshots" con historial de tableros, se guarda el nuevo cada vez que se hace un movimiento
    fun getBoards(): List<Board>
    fun updateState(movement: Movement): GameState
    fun getCurrentTurn(): Color

}