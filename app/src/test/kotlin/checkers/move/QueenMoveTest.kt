package checkers.move

import edu.austral.dissis.checkers.factory.createCheckersQueenTestGame
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.game.GameState
import org.junit.Test

class QueenMoveTest {

    private val initialGameState: GameState = createCheckersQueenTestGame()

    @Test
    fun `test simple move`() {
        val afterMove = initialGameState.movePiece(Movement(Position(2, 2), Position(3, 3)))
        assert(afterMove.getActualBoard().getPieceByPosition(Position(3, 3)) != null)
    }

    @Test
    fun `test simple move 3`() {
        val afterMove = initialGameState.movePiece(Movement(Position(2, 2), Position(1, 3)))
        assert(afterMove.getActualBoard().getPieceByPosition(Position(1, 3))!!.getColor() == initialGameState.getCurrentTurn())
    }

    @Test
    fun `test valid move`() {
        val afterMove = initialGameState.movePiece(Movement(Position(2, 2), Position(3, 1)))
        assert(afterMove.getActualBoard().getPieceByPosition(Position(3, 1)) != null)
    }

    @Test
    fun `eat`() {
        val afterMove = initialGameState.movePiece(Movement(Position(2, 2), Position(0, 0)))
        assert(afterMove.getActualBoard().getPieceByPosition(Position(1, 1)) == null)
    }

}