package edu.austral.dissis.chess.factory

import edu.austral.dissis.Position
import edu.austral.dissis.chess.board.ClassicBoard
import edu.austral.dissis.chess.factory.pieceFactory.*
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece

class BoardFactory {
    companion object {
        fun createClassicChessBoard(): ClassicBoard {

            var map: Map<Position, Piece> = mutableMapOf(
                /** Ubico torres */
                Position(0, 0) to RookInitializer().initialize(Color.WHITE),
                Position(0, 7) to RookInitializer().initialize(Color.WHITE),
                Position(7, 0) to RookInitializer().initialize(Color.BLACK),
                Position(7, 7) to RookInitializer().initialize(Color.BLACK),

                /** Ubico caballos */
                Position(0, 1) to KnightInitializer().initialize(Color.WHITE),
                Position(0, 6) to KnightInitializer().initialize(Color.WHITE),
                Position(7, 1) to KnightInitializer().initialize(Color.BLACK),
                Position(7, 6) to KnightInitializer().initialize(Color.BLACK),

                /** Ubico alfiles */
                Position(0, 2) to BishopInitializer().initialize(Color.WHITE),
                Position(0, 5) to BishopInitializer().initialize(Color.WHITE),
                Position(7, 2) to BishopInitializer().initialize(Color.BLACK),
                Position(7, 5) to BishopInitializer().initialize(Color.BLACK),

                /** Ubico reinas */
                Position(0, 3) to QueenInitializer().initialize(Color.WHITE),
                Position(7, 3) to QueenInitializer().initialize(Color.BLACK),

                /** Ubico reyes */
                Position(0, 4) to KingInitializer().initialize(Color.WHITE),
                Position(7, 4) to KingInitializer().initialize(Color.BLACK),
            )

            /** Ubico peones */
            for (i in 0..7) {
                map = map.plus(Position(1, i) to PawnInitializer().initialize(Color.WHITE))
                map = map.plus(Position(6, i) to PawnInitializer().initialize(Color.BLACK))
            }

            return ClassicBoard(8, 8, map)
        }
    }
}