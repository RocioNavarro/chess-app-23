package chess.board

import edu.austral.dissis.Position
import edu.austral.dissis.chess.board.RectangularBoard
import edu.austral.dissis.chess.factory.pieceFactory.PawnInitializer
import edu.austral.dissis.chess.factory.pieceFactory.RookInitializer
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.piece.PieceType
import org.junit.Assert
import org.junit.Test

class UpdateBoardTest {

    @Test
    fun updateBoard() {
        val initialBoard = RectangularBoard(8, 8, mapOf(Position(0, 0) to RookInitializer().initialize(Color.WHITE)))
        val movement = Movement(Position(0, 0), Position(1, 0))
        val updatedBoard = initialBoard.update(movement)

        Assert.assertNull(updatedBoard.getPieceByPosition(Position(0, 0)))
        Assert.assertEquals(PieceType.ROOK, updatedBoard.getPieceByPosition(Position(1, 0))?.getType())
    }


    @Test
    fun updateBoardWithPawn() {
        val initialBoard = RectangularBoard(8, 8, mapOf(Position(1, 1) to PawnInitializer().initialize(Color.WHITE)))
        val movement = Movement(Position(1, 1), Position(2, 1))
        val updatedBoard = initialBoard.update(movement)

        Assert.assertNull(updatedBoard.getPieceByPosition(Position(1, 1)))
        Assert.assertEquals(PieceType.PAWN, updatedBoard.getPieceByPosition(Position(2, 1))?.getType())
    }

}