package edu.austral.dissis.checkers.factory

import edu.austral.dissis.COLUMNS
import edu.austral.dissis.ROWS
import edu.austral.dissis.checkers.factory.pieceFactory.ManInitializer
import edu.austral.dissis.checkers.factory.pieceFactory.QueenInitializer
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece

fun createCheckersBoard(): Board {
    val map: MutableMap<Position, Piece> = mutableMapOf()

    for (i in 0 until ROWS) {
        for (j in 0 until COLUMNS) {
            // Inicializar fichas blancas en las filas 0 a 2
            if (i in 0..2 && (i + j) % 2 == 1) {
                map[Position(i, j)] = ManInitializer().initialize(Color.WHITE)
            }
            // Inicializar fichas negras en las filas 5 a 7
            else if (i in 5 until ROWS && (i + j) % 2 == 1) {
                map[Position(i, j)] = ManInitializer().initialize(Color.BLACK)
            }
        }
    }
    return RectangularBoard(getWidth(8), getHeight(8), map)
}


private fun getWidth(minSize: Int): Int {
    return if (ROWS < minSize) minSize else ROWS
}

private fun getHeight(minSize: Int): Int {
    return if (COLUMNS < minSize) minSize else COLUMNS
}

fun createCheckersQueenTestBoard() : Board {
    val map: MutableMap<Position, Piece> = mutableMapOf()

    map[Position(2, 2)] = QueenInitializer().initialize(Color.WHITE)
    map[Position(1, 1)] = ManInitializer().initialize(Color.BLACK)
    return RectangularBoard(getWidth(8), getHeight(8), map)
}
