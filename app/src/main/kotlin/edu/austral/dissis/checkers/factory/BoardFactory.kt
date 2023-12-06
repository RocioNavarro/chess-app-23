package edu.austral.dissis.checkers.factory

import edu.austral.dissis.COLUMNS
import edu.austral.dissis.ROWS
import edu.austral.dissis.checkers.factory.pieceFactory.ManInitializer
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece

    fun createCheckersBoard(): Board {
        val map: MutableMap<Position, Piece> = mutableMapOf()

        for (i in 0..2) {
            val startColumn = if (i % 2 == 1) 2 else 1 // para que empiece desde la segunda columna para filas impares
            for (j in startColumn..7 step 2) {
                map[Position(i, j)] = ManInitializer().initialize(Color.WHITE)
            }
        }

        map[Position(1, 0)] = ManInitializer().initialize(Color.WHITE)

        for (i in 5..7) {
            val startColumn =
                if (i % 2 == 1) 2 else 1 // Start from 2nd column for odd rows and 1st column for even rows
            for (j in startColumn..7 step 2) {
                map[Position(i, j)] = ManInitializer().initialize(Color.BLACK)
            }
        }

        map[Position(7, 0)] = ManInitializer().initialize(Color.BLACK)
        map[Position(5, 0)] = ManInitializer().initialize(Color.BLACK)

        return RectangularBoard(getWidth(8), getHeight(8), map)
    }


    private fun getWidth(minSize: Int): Int {
        return if (ROWS < minSize) minSize else ROWS
    }

    private fun getHeight(minSize: Int): Int {
        return if (COLUMNS < minSize) minSize else COLUMNS
    }
