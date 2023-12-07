package edu.austral.dissis.common.board

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.piece.Piece

class RectangularBoard(private val sizeX: Int,
                       private val sizeY: Int,
                       private val piecePositions: Map<Position, Piece> ) : Board {

    override fun getSizeX(): Int {
        return sizeX
    }

    override fun getSizeY(): Int {
        return sizeY
    }

    override fun getPieceByPosition(position: Position): Piece? {
        return piecePositions[position]
    }

    override fun getPositionByPiece(piece: Piece): Position? {
        for (position in piecePositions.keys) {
            if (piecePositions[position]?.getId() == piece.getId()) {
                return position
            }
        }
        return null
    }

    override fun getPiecesPositions(): Map<Position, Piece> {
        return piecePositions
    }

    override fun update(movement: Movement): Board {
        val newPiecePositions = piecePositions.toMutableMap()
        newPiecePositions.remove(movement.from)
        val piece : Piece = piecePositions[movement.from]!!.incrementMoveCounter()
        newPiecePositions[movement.to] = piece
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }

    override fun getOccupiedPositions(): List<Position> {
        return piecePositions.keys.toList()
    }

    override fun removePieceByPosition(position: Position): Board {
        val newPiecePositions = piecePositions.toMutableMap()
        newPiecePositions.remove(position)
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }

    override fun getPieces(): List<Piece> {
        return piecePositions.values.toList()
    }

    override fun updatePieceByPosition(position: Position, piece: Piece): Board {
        val newPiecePositions = piecePositions.toMutableMap()
        newPiecePositions[position] = piece
        return RectangularBoard(sizeX, sizeY, newPiecePositions)
    }
}