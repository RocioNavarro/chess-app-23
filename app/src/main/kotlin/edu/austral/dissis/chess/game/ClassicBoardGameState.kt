package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.ClassicBoard
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color

class ClassicBoardGameState (private val boards : List<ClassicBoard>) : GameState{
    override fun getActualBoard(): Board {
        return boards.last();
    }

    override fun getBoards(): List<Board> {
        return boards;
    }

    override fun updateState(movement: Movement): GameState {
        TODO("Not yet implemented")
        //Al mover una pieza, actualizar tablero y guardar el nuevo estado
    }

    override fun getCurrentTurn(): Color {
        TODO("Not yet implemented")
    }
}