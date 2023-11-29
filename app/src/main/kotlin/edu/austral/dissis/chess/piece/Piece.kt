package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

data class Piece(val id: String,
                 val color: Color,
                 val type : PieceType,
                 val validator : Validator,
                 private val moveCounter : Int = 0){
    fun getMoveCounter(): Int {
        return moveCounter
    }

    fun validateMove(movement: Movement, gameState: GameState): ValidatorResponse {
        return validator.validate(movement, gameState)
    }

}
