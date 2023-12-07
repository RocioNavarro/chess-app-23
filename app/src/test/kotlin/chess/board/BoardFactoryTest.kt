package chess.board

import edu.austral.dissis.common.Position
import edu.austral.dissis.chess.factory.createClassicChessBoard
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.PieceType
import org.junit.Assert
import org.junit.Test

class BoardFactoryTest {

    @Test
    fun createChessBoard() {
        val chessBoard: RectangularBoard = createClassicChessBoard()

        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 0))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(0, 0))?.getType())

        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 7))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(0, 7))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 0))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(7, 0))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 7))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(7, 7))?.getType())

    }
}