package edu.austral.dissis.common.validator.winCondition

import edu.austral.dissis.common.game.GameState

interface WinCondition {
    fun isWin(gameState: GameState) : Boolean
}