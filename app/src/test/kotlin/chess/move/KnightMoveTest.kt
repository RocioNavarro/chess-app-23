package chess.move

import edu.austral.dissis.chess.factory.createKnightTestGame
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.game.FinishGameState
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.InvalidMoveGameState
import org.junit.Test

class KnightMoveTest {

    private val initialGameState: GameState = createKnightTestGame()

    @Test
    fun moveUpRight() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(5, 4)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(5, 4)) != null)
    }

    @Test
    fun moveUpLeft() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(5, 2)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(5, 2)) != null)
    }

    @Test
    fun moveDownRight() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(1, 4)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(1, 4)) != null)
    }

    @Test
    fun moveDownLeft() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(1, 2)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(1, 2)) != null)
    }

    @Test
    fun moveRightUp() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 5)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(4, 5)) != null)
    }

    @Test
    fun moveRightDown() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 5)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(2, 5)) != null)
    }

    @Test
    fun moveLeftUp() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 1)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(4, 1)) != null)
    }

    @Test
    fun moveLeftDown() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 1)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(2, 1)) != null)
    }

    @Test
    fun eat() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(5, 4)))
        assert(afterMoveGameState is FinishGameState)
    }

    @Test
    fun invalidMovement() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 3)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }

    @Test
    fun invalidMovement2() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(3, 5)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }

    @Test
    fun invalidMovement3() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 4)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }

    @Test
    fun invalidMovement4() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 2)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }

    @Test
    fun invalidMovement5() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 2)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }

    @Test
    fun invalidMovement7() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(5, 3)))
        assert(afterMoveGameState is InvalidMoveGameState)
    }
}
