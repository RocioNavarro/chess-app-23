package edu.austral.dissis.common.validator

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement

interface Validator {
    fun validate(movement: Movement, gameState: GameState): ValidatorResponse
}