package edu.austral.dissis.chess.factory

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.chess.factory.pieceFactory.*
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece

fun createClassicChessBoard(): RectangularBoard {
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

    return RectangularBoard(8, 8, map)
}

/** Idem classicChess pero con tablero de 10x8 y se agregan Chancellor y Archbishop */
fun createCapablancaChessBoard(): Board {
    var map: Map<Position, Piece> = mutableMapOf(
        Position(0, 0) to RookInitializer().initialize(Color.WHITE),
        Position(0, 9) to RookInitializer().initialize(Color.WHITE),
        Position(9, 0) to RookInitializer().initialize(Color.BLACK),
        Position(9, 9) to RookInitializer().initialize(Color.BLACK),

        Position(0, 1) to KnightInitializer().initialize(Color.WHITE),
        Position(0, 8) to KnightInitializer().initialize(Color.WHITE),
        Position(9, 1) to KnightInitializer().initialize(Color.BLACK),
        Position(9, 8) to KnightInitializer().initialize(Color.BLACK),

        Position(0, 3) to BishopInitializer().initialize(Color.WHITE),
        Position(0, 6) to BishopInitializer().initialize(Color.WHITE),
        Position(9, 3) to BishopInitializer().initialize(Color.BLACK),
        Position(9, 6) to BishopInitializer().initialize(Color.BLACK),

        Position(0, 4) to QueenInitializer().initialize(Color.WHITE),
        Position(9, 4) to QueenInitializer().initialize(Color.BLACK),

        Position(0, 5) to KingInitializer().initialize(Color.WHITE),
        Position(9, 5) to KingInitializer().initialize(Color.BLACK),

        Position(0, 7) to ChancellorInitializer().initialize(Color.WHITE),
        Position(9, 7) to ChancellorInitializer().initialize(Color.BLACK),

        Position(0, 2) to ArchbishopInitializer().initialize(Color.WHITE),
        Position(9, 2) to ArchbishopInitializer().initialize(Color.BLACK),
    )

    for (i in 0..9) {
        map = map.plus(Position(1, i) to PawnInitializer().initialize(Color.WHITE))
        map = map.plus(Position(8, i) to PawnInitializer().initialize(Color.BLACK))
    }

    return RectangularBoard(10, 10, map)
}

fun createRookTestBoard(): Board {
    val map: Map<Position, Piece> = mutableMapOf(
        Position(3, 3) to RookInitializer().initialize(Color.WHITE),
        Position(3, 5) to RookInitializer().initialize(Color.BLACK),
    )
    return RectangularBoard(8, 8, map)
}

fun createBishopTestBoard(): Board {
    val map: Map<Position, Piece> = mutableMapOf(
        Position(3, 3) to BishopInitializer().initialize(Color.WHITE),
        Position(4, 4) to BishopInitializer().initialize(Color.BLACK),
    )
    return RectangularBoard(8, 8, map)
}

fun createKnightTestBoard(): Board {
    val map: Map<Position, Piece> = mutableMapOf(
        Position(3, 3) to KnightInitializer().initialize(Color.WHITE),
        Position(3, 4) to KnightInitializer().initialize(Color.WHITE),
        Position(4, 5) to KnightInitializer().initialize(Color.BLACK),
    )
    return RectangularBoard(8, 8, map)
}

fun createKingTestBoard(): Board {
    val map: Map<Position, Piece> = mutableMapOf(
        Position(3, 3) to KingInitializer().initialize(Color.WHITE),
        Position(3, 4) to KingInitializer().initialize(Color.WHITE),
        Position(4, 4) to KingInitializer().initialize(Color.BLACK),
    )
    return RectangularBoard(8, 8, map)
}

fun createQueenTestBoard(): Board {
    val map: Map<Position, Piece> = mutableMapOf(
        Position(3, 3) to QueenInitializer().initialize(Color.WHITE),
        Position(3, 5) to QueenInitializer().initialize(Color.WHITE),
        Position(4, 4) to QueenInitializer().initialize(Color.BLACK),
    )
    return RectangularBoard(8, 8, map)
}
