package edu.austral.dissis.common.validator.obstacle

import edu.austral.dissis.Position
import edu.austral.dissis.chess.game.ClassicBoardGameState
import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement

class EmptyDiagonalValidator : edu.austral.dissis.chess.validator.Validator {

    override fun validate(movement: Movement, gameState: GameState): edu.austral.dissis.chess.validator.ValidatorResponse {
        val positions: Board = gameState.getActualBoard() as Board
        val fromX = movement.from.row
        val fromY = movement.from.column
        val toX = movement.to.row
        val toY = movement.to.column
        var currentX = fromX + 1
        var currentY = fromY + 1

        while (currentX < toX && currentY < toY) {
            val positionToCheck = Position(currentX, currentY)
            if (positions.getPieceByPosition(positionToCheck) != null) {
                return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("Hay piezas en el camino")
            }
            currentX++
            currentY++
        }

        return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("Movimiento OK")
    }

}