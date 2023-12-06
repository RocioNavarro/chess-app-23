package edu.austral.dissis.common.validator.preCondition.obstacleValidator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

class EmptyHorizontalValidator : Validator {

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        if (movement.from.row != movement.to.row) return ValidatorResponse.ValidatorResultInvalid("ERROR: el movimiento no es horizontal.")

        val positions: Board = gameState.getActualBoard() as Board

        val fromX = movement.from.row
        val fromY = movement.from.column
        val toY = movement.to.column

        return isEmptyPath(fromX, fromY, toY, positions)
    }

    private fun isEmptyPath(fromX: Int, fromY: Int, toY: Int, positions: Board): ValidatorResponse {
        val typeMove =
            if (fromY < toY)     "right movement"
            else                 "left movement"

        return if (typeMove == "right movement")     isEmptyPathRightMovement(fromX, fromY, toY, positions)
        else                                         isEmptyPathLeftMovement(fromX, fromY, toY, positions)
    }

    private fun isEmptyPathRightMovement(fromX: Int, fromY: Int, toY: Int, positions: Board): ValidatorResponse {
        for (y in fromY + 1..<toY) {
            val positionToCheck = Position(fromX, y)
            if (positions.getPieceByPosition(positionToCheck) != null) {
                return ValidatorResponse.ValidatorResultInvalid("ERROR: Hay piezas en el camino")
            }
        }
        return ValidatorResponse.ValidatorResultValid("Movimiento OK")
    }

    private fun isEmptyPathLeftMovement(fromX: Int, fromY: Int, toY: Int, positions: Board): ValidatorResponse {
        for (y in fromY - 1 downTo toY +1) {
            val positionToCheck = Position(fromX, y)
            if (positions.getPieceByPosition(positionToCheck) != null) {
                return ValidatorResponse.ValidatorResultInvalid("ERROR: Hay piezas en el camino")
            }
        }
        return ValidatorResponse.ValidatorResultValid("Movimiento OK")
    }
}



