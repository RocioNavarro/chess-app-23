package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.GameState

/** Voy a validar las condiciones luego de hacer un movimiento: enroque, jaque? */
interface PostConditionValidator {
    fun validate(gameState: GameState, updatedBoard: Board): PostConditionResult
}