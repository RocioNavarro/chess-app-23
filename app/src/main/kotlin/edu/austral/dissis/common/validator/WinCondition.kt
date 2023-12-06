package edu.austral.dissis.common.validator

import edu.austral.dissis.chess.game.GameState

interface WinCondition {
    fun isWin(gameState: GameState) : Boolean
}