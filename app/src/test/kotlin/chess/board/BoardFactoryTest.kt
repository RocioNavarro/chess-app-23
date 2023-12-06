package chess.board

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.factory.BoardFactory
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.PieceType
import org.junit.Assert
import org.junit.Test

class BoardFactoryTest {

    @Test
    fun createChessBoard() {
        val chessBoard: Board = BoardFactory.createClassicChessBoard()

        /** Torres */
        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 0))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(0, 0))?.getType())

        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 7))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(0, 7))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 0))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(7, 0))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 7))?.getColor())
        Assert.assertEquals(PieceType.ROOK, chessBoard.getPieceByPosition(Position(7, 7))?.getType())


        /** Alfiles */
        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 2))?.getColor())
        Assert.assertEquals(PieceType.BISHOP, chessBoard.getPieceByPosition(Position(0, 2))?.getType())

        Assert.assertEquals(Color.WHITE, chessBoard.getPieceByPosition(Position(0, 5))?.getColor())
        Assert.assertEquals(PieceType.BISHOP, chessBoard.getPieceByPosition(Position(0, 5))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 2))?.getColor())
        Assert.assertEquals(PieceType.BISHOP, chessBoard.getPieceByPosition(Position(7, 2))?.getType())

        Assert.assertEquals(Color.BLACK, chessBoard.getPieceByPosition(Position(7, 5))?.getColor())
        Assert.assertEquals(PieceType.BISHOP, chessBoard.getPieceByPosition(Position(7, 5))?.getType())

    }
}