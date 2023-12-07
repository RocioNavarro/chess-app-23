package common.validator

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.chess.validator.preCondition.ChessTurnValidator
import edu.austral.dissis.chess.validator.winCondition.CheckMateValidator
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.ValidatorResponse
import edu.austral.dissis.common.validator.gameCondition.movement.DiagonalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.HorizontalMoveValidator
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalMoveValidator
import org.junit.Test

internal class DirectionsTest {

    @Test
    fun testStraightValidator() {
        val validator = VerticalMoveValidator()

        val gameState: GameState = GameStateImp(listOf(RectangularBoard(8, 8, emptyMap())),
            CheckMateValidator(),
            ChessTurnValidator(Color.WHITE),
            listOf( validator ),
            listOf())

        val verticalMovement = Movement(Position(0, 0), Position(5, 0))
        assert(validator.validate(verticalMovement, gameState) is ValidatorResponse.ValidatorResultValid)

        // Movimiento horizontal
        val horizontalMovement = Movement(Position(0, 0), Position(0, 5))
        assert(validator.validate(horizontalMovement, gameState) is ValidatorResponse.ValidatorResultInvalid)

    }

    @Test
    fun testHorizontalValidator() {
        val validator = HorizontalMoveValidator()

        val gameState: GameState = GameStateImp(listOf(RectangularBoard(8, 8, emptyMap())),
            CheckMateValidator(),
            ChessTurnValidator(Color.WHITE),
            listOf( validator ),
            listOf())

        val horizontalMovement = Movement(Position(0, 0), Position(0, 5))
        assert(validator.validate(horizontalMovement, gameState) is ValidatorResponse.ValidatorResultValid)

        val verticalMovement = Movement(Position(0, 0), Position(5, 0))
        assert(validator.validate(verticalMovement, gameState) is ValidatorResponse.ValidatorResultInvalid)
    }

    @Test
    fun testDiagonalValidator() {
        val validator = DiagonalMoveValidator()

        val gameState: GameState = GameStateImp(listOf(RectangularBoard(8, 8, emptyMap())),
            CheckMateValidator(),
            ChessTurnValidator(Color.WHITE),
            listOf( validator ),
            listOf())

        val diagonalMovement = Movement(Position(0, 0), Position(5, 5))
        assert(validator.validate(diagonalMovement, gameState) is ValidatorResponse.ValidatorResultValid)

        // horizontal invalido
        val horizontalMovement = Movement(Position(0, 0), Position(0, 5))
        assert(validator.validate(horizontalMovement, gameState) is ValidatorResponse.ValidatorResultInvalid)

        // vertical invalido
        val verticalMovement = Movement(Position(0, 0), Position(5, 0))
        assert(validator.validate(verticalMovement, gameState) is ValidatorResponse.ValidatorResultInvalid)
    }
}
