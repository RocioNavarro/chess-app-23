package edu.austral.dissis.common.piece

import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece

/**
    Las implementaciones de PieceInitializer van a tener las reglas de cada pieza con los And - Or validators
 **/
interface PieceInitializer {

    fun initialize(color: Color): Piece
    fun initialize(color: Color, id: String) : Piece
}