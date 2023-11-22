package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.Piece

/** todo debería tener el sentido para el que se mueve las piezas
    para x → 1 der, -1 izq
    para y → 1 arriba, -1 abajo
    Las implementaciones de PieceInitializer van a tener las reglas de cada pieza con los And - Or validators
 **/
interface PieceInitializer {
    fun initialize(color: Color): Piece
}