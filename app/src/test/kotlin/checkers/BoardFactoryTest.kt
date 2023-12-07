package checkers

import edu.austral.dissis.checkers.factory.createCheckersBoard
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.PieceType
import org.junit.Assert
import org.junit.Test

class BoardFactoryTest {

    @Test
    fun createBoard() {
        val checkersBoard: Board = createCheckersBoard()

        for (i in 0..2) {
            val startColumn =
                if (i % 2 == 0) 1 else 0
            for (j in startColumn..7 step 2) {
                Assert.assertEquals(Color.WHITE, checkersBoard.getPieceByPosition(Position(i, j))?.getColor())
                Assert.assertEquals(PieceType.PAWN, checkersBoard.getPieceByPosition(Position(i, j))?.getType())
            }
        }

        for (i in 5..7) {
            val startColumn =
                if (i % 2 == 0) 1 else 0
            for (j in startColumn..7 step 2) {
                Assert.assertEquals(Color.BLACK, checkersBoard.getPieceByPosition(Position(i, j))?.getColor())
                Assert.assertEquals(PieceType.PAWN, checkersBoard.getPieceByPosition(Position(i, j))?.getType())
            }
        }
    }

}

