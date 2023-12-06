package edu.austral.dissis.common.board

import edu.austral.dissis.common.Position
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Piece

interface Board {
    fun getSizeX() : Int //Cantidad de columnas
    fun getSizeY() : Int //Cantidad de filas
    fun getPiecesPositions() : Map<Position, Piece>
    fun getPositionByPiece(piece: Piece) : Position?
    fun getPieceByPosition(position: Position) : Piece?
    fun getOccupiedPositions(): List<Position>
    fun getPieces() : List<Piece>
    fun update(movement: Movement): Board
    fun removePieceByPosition(position: Position): Board
    fun updatePieceByPosition(position: Position, piece: Piece): Board

}