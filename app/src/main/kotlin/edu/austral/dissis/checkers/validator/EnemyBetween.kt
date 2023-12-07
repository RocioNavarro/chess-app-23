package edu.austral.dissis.checkers.validator

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** Chequeo si hay una pieza del otro equipo en el medio */
class EnemyBetween : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val middlePosition : Position = getMiddlePosition(movement)
        val middlePiece = gameState.getActualBoard().getPieceByPosition(middlePosition)

        return if ( isEnemy(middlePiece, gameState.getCurrentTurn()) ){
            ValidatorResponse.ValidatorResultValid("Es enemigo")
        }else{
            ValidatorResponse.ValidatorResultInvalid("No es enemigo")
        }
    }

    private fun getMiddlePosition(move: Movement): Position{
        val midRow = (move.from.row + move.to.row) / 2
        val midColumn = (move.from.column + move.to.column) / 2
        return Position(midRow, midColumn)
    }

    private fun isEnemy(piece: Piece?, currentTurn: Color): Boolean{
        if(piece == null) return false
        return piece.getColor() != currentTurn
    }
}