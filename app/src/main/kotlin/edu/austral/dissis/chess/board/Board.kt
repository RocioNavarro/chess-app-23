package edu.austral.dissis.chess.board

import edu.austral.dissis.Position
import edu.austral.dissis.chess.piece.Piece

interface Board {
    fun getSizeX() : Int //Cantidad de columnas
    fun getSizeY() : Int //Cantidad de filas
    fun getPiecesPositions() : Map<Position, Piece>
    fun getPositionByPiece(piece: Piece) : Position?
    fun getPositions() : Map<Position, Piece>
    fun getPieceByPosition(position: Position) : Piece?
    fun getOccupiedPositions(): List<Position>
    fun getPieces() : List<Piece>
}