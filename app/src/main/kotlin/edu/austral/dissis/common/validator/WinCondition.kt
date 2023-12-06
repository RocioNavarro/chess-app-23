package edu.austral.dissis.common.validator

import edu.austral.dissis.common.game.GameState

interface WinCondition {
    fun isWin(gameState: GameState) : Boolean
}