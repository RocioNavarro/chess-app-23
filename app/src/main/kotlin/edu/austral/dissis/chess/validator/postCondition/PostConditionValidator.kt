package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState

/** Voy a validar las condiciones luego de hacer un movimiento: enroque, jaque? */
interface PostConditionValidator {
    fun validate(gameState: GameState, updatedBoard: Board): PostConditionResult
}