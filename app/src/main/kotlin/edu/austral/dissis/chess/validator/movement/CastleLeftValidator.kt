package edu.austral.dissis.chess.validator.movement

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse

/** ENROQUE LARGO: Valido si puedo hacer ENROQUE hacia la izquierda
 * Estando la torre y el rey separados, se intercambian y quedan en una posicion intermedia
 * */
class CastleLeftValidator: Validator {
    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val board = gameState.getActualBoard()
        if (board.getPieceByPosition(movement.from)!!.getType() != PieceType.KING) return ValidatorResponse.ValidatorResultInvalid("La pieza que estas moviendo no es un rey")

        if (movement.to.row != movement.from.row) return ValidatorResponse.ValidatorResultInvalid("El rey solo se puede mover en horizontal (Para el enroque)")

        if (movement.to.column != movement.from.column - 2 ) return ValidatorResponse.ValidatorResultInvalid("El rey solo se puede mover dos casillas a la derecha (Para el enroque)")

        if (!noPiecesBetweenCastling(board, movement)) return ValidatorResponse.ValidatorResultInvalid("No hay piezas entre el rey y la torre")

        val rook = board.getPieceByPosition(Position(movement.from.row,movement.from.column - 4)) ?: return ValidatorResponse.ValidatorResultInvalid("No hay torre en la posicion indicada")

        return ValidatorResponse.ValidatorResultValid("proceda!")
    }

    private fun noPiecesBetweenCastling(board: Board, movement: Movement): Boolean {
        for (i in movement.from.column + 1 until movement.to.column) {
            val auxPos: Position = Position(movement.from.row, i)
            if (board.getPieceByPosition(auxPos) != null) return false
        }
        return true
    }
}