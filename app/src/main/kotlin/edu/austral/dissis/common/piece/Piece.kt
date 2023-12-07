package edu.austral.dissis.common.piece

import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

data class Piece(private val id: String,
                 private val color: Color,
                 private val type : PieceType,
                 val validator : Validator,
                 private var moveCounter : Int = 0){
    fun getMoveCounter(): Int {
        return moveCounter
    }

    fun getColor() : Color {
        return color
    }

    fun getId() : String{
        return id
    }

    fun getType(): PieceType {
        return type
    }

    fun validateMove(movement: Movement, gameState: GameState): edu.austral.dissis.common.validator.ValidatorResponse {
        return validator.validate(movement, gameState)
    }

    fun incrementMoveCounter(){
        moveCounter++;
    }

}
