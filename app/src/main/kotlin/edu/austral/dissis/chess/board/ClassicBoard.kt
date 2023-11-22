package edu.austral.dissis.chess.board

import edu.austral.dissis.Position
import edu.austral.dissis.chess.piece.Piece

class ClassicBoard(private val sizeX: Int,
                   private val sizeY: Int,
                   private val piecesPositions: Map<Position, Piece> ) : Board {
    override fun getSizeX(): Int {
        return sizeX;
    }

    override fun getSizeY(): Int {
        return sizeY;
    }

    override fun getPiecesPositions(): Map<Position, Piece> {
        return piecesPositions;
    }

    override fun getPositionByPiece(piece: Piece): Position? {
        for (position in piecesPositions.keys){
            if (piece == piecesPositions[position]){
                return position;
            }
        }
        return null;
    }

    override fun getPositions(): Map<Position, Piece> {
        return piecesPositions;
    }

    override fun getPieceByPosition(position: Position): Piece? {
        return piecesPositions[position];
    }

    override fun getOccupiedPositions(): List<Position> {
        return piecesPositions.keys.toList()
    }

    override fun getPieces(): List<Piece> {
        return piecesPositions.values.toList()
    }
}