package edu.austral.dissis.common.validator.obstacle

import edu.austral.dissis.Position
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.ValidatorResponse
import kotlin.math.abs

class EmptyDiagonalValidator : edu.austral.dissis.chess.validator.Validator {

    /** Verifico que el movimiento sea diagonal viendo si el movimiento vertical es igual al horizontal */
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        if (abs(movement.from.row - movement.to.row) != abs(movement.from.column - movement.to.column)) {
            return ValidatorResponse.ValidatorResultInvalid("No es un movimiento diagonal")
        }

        /** Recorro todas las posiciones por las que pasaría la pieza y voy viendo si están vacías */
        for (position in getDiagonalPath(movement.from, movement.to)) {
            if (gameState.getActualBoard().getPieceByPosition(position) != null) {
                return ValidatorResponse.ValidatorResultInvalid("Hay piezas en el camino")
            }
        }

        return ValidatorResponse.ValidatorResultValid("OK")
    }

    /** Me da una lista con todas las posiciones por las que va a pasar la pieza moviendose de forma diagonal */
    private fun getDiagonalPath(from: Position, to: Position): List<Position> {
        val path = mutableListOf<Position>()
        var currentRow = from.row
        var currentColumn = from.column

        while (currentRow != to.row && currentColumn != to.column) {
            currentRow += if (currentRow < to.row) 1 else -1
            currentColumn += if (currentColumn < to.column) 1 else -1
            path.add(Position(currentRow, currentColumn))
        }

        return path
    }

}