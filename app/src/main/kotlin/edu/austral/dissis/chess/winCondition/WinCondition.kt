package edu.austral.dissis.chess.winCondition

import edu.austral.dissis.chess.game.GameState

interface WinCondition {
    fun isWin(gameState: GameState) : Boolean
}