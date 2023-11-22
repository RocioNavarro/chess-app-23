package edu.austral.dissis.chess.validator.winCondition

import edu.austral.dissis.chess.game.GameState

interface WinCondition {
    fun isWin(gameState: GameState) : Boolean
}