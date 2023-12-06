package edu.austral.dissis.common.validator

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement

interface Validator {
    fun validate(movement: Movement, gameState: GameState): ValidatorResponse
}