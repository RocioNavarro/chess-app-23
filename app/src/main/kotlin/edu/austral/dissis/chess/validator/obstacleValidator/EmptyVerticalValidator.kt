package edu.austral.dissis.chess.validator.obstacleValidator

import edu.austral.dissis.Position
import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.ClassicBoardGameState
import edu.austral.dissis.chess.movement.Movement

class EmptyVerticalValidator : edu.austral.dissis.chess.validator.Validator {

    override fun validate(movement: Movement, gameState: ClassicBoardGameState): edu.austral.dissis.chess.validator.ValidatorResponse {
        val positions : Board = gameState.getActualBoard() as Board

        val fromX = movement.from.row
        val toX = movement.to.row
        val toY = movement.to.column

        for (x in fromX + 1..toX) {
            val positionToCheck = Position(x, toY)
            if (positions.getPieceByPosition(positionToCheck) != null) {
                return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultInvalid("Hay piezas en el camino")
            }
        }

        return edu.austral.dissis.chess.validator.ValidatorResponse.ValidatorResultValid("Movimiento OK")
    }
}