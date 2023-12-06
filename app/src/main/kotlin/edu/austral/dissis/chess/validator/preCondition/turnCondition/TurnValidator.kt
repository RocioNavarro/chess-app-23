package edu.austral.dissis.chess.validator.preCondition.turnCondition

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.ValidatorResponse

interface TurnValidator {

    fun getTurn() : Color

    fun nextTurn() : TurnValidator

    /** Guardar el movimiento me va a servir para la implementacion en damas para cuando coma doble */
    fun validateTurn(movement: Movement, gameState: GameState) : edu.austral.dissis.common.validator.ValidatorResponse
}