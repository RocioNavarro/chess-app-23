package edu.austral.dissis.common.validator.winCondition

import edu.austral.dissis.common.game.GameState

class ExtinctionCondition : WinCondition {

    override fun isWin(gameState: GameState): Boolean {
        val color = gameState.getCurrentTurn()
        /** Me fijo si todas las piezas que quedan son del equipo que hizo el movimiento */
        return gameState.getActualBoard().getPieces().all { it.getColor() == color }
    }
}