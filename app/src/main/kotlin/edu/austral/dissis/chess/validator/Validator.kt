package edu.austral.dissis.chess.validator

import edu.austral.dissis.chess.movement.Movement

interface Validator {
    fun validate(movement: Movement, gameState: GameState): ValidatorResponse
}