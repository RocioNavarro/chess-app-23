package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece

/**
    Las implementaciones de PieceInitializer van a tener las reglas de cada pieza con los And - Or validators
 **/
interface PieceInitializer {

    fun initialize(color: Color): Piece
    fun initialize(color: Color, id: String) : Piece
}