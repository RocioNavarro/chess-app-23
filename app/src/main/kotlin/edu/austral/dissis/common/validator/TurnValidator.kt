package edu.austral.dissis.common.validator

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.piece.Color

interface TurnValidator {

    fun getTurn() : Color

    fun nextTurn(gameState: GameState) : TurnValidator

    /** Guardar el movimiento me va a servir para la implementacion en damas para cuando coma doble */
    fun validateTurn(movement: Movement, gameState: GameState) : ValidatorResponse
}