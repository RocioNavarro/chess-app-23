package edu.austral.dissis.common.board

import edu.austral.dissis.common.Position
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Piece

class RectangularBoard(private val sizeX: Int,
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

    override fun getPieceByPosition(position: Position): Piece? {
        return piecesPositions[position];
    }

    override fun getOccupiedPositions(): List<Position> {
        return piecesPositions.keys.toList()
    }

    override fun getPieces(): List<Piece> {
        return piecesPositions.values.toList()
    }

    override fun update(movement: Movement): Board {
        val newPiecePositions = piecesPositions.toMutableMap()
        newPiecePositions.remove(movement.from)
        newPiecePositions[movement.to] = piecesPositions[movement.from]!!
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }

    override fun removePieceByPosition(position: Position): Board {
        val newPiecePositions = piecesPositions.toMutableMap()
        newPiecePositions.remove(position)
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }

    override fun updatePieceByPosition(position: Position, piece: Piece): Board {
        val newPiecePositions = piecesPositions.toMutableMap()
        newPiecePositions[position] = piece
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }
}