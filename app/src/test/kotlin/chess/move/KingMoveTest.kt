package chess.move

import edu.austral.dissis.chess.factory.createKingTestGame
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.game.GameState
import org.junit.Test

class KingMoveTest {

    private val initialGameState: GameState = createKingTestGame()

    @Test
    fun moveUp() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 3)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(4, 3)) != null)
    }

    @Test
    fun moveDown() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 3)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(2, 3)) != null)
    }

    @Test
    fun moveLeft() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(3, 2)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(3, 2)) != null)
    }

    @Test
    fun moveRight() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(3, 4)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(3, 4)) != null)
    }

    @Test
    fun moveUpRight() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 4)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(4, 4)) != null)
    }

    @Test
    fun moveUpLeft() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(4, 2)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(4, 2)) != null)
    }

    @Test
    fun moveDownRight() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 4)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(2, 4)) != null)
    }

    @Test
    fun moveDownLeft() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(2, 2)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(2, 2)) != null)
    }

    @Test
    fun invalidMovement() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(5, 5)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(3, 3)) != null)
    }


    @Test
    fun invalidMovement3Obstacle() {
        val afterMoveGameState = initialGameState.movePiece(Movement(Position(3, 3), Position(3, 7)))
        assert(afterMoveGameState.getActualBoard().getPieceByPosition(Position(3, 3)) != null)
    }
}
