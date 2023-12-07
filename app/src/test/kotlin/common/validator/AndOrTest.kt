package common.validator

import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.chess.validator.preCondition.ChessTurnValidator
import edu.austral.dissis.chess.validator.winCondition.CheckMateValidator
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.RectangularBoard
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse
import edu.austral.dissis.common.validator.gameCondition.composition.AndValidator
import edu.austral.dissis.common.validator.gameCondition.composition.OrValidator
import edu.austral.dissis.common.validator.gameCondition.movement.VerticalMoveValidator
import edu.austral.dissis.common.validator.preCondition.obstacleValidator.LegalPositionValidator
import org.junit.Test

class AndOrTest {

    @Test
    fun testAndValidator() {
        val validator: List<Validator> = listOf( AndValidator(
            listOf(
                VerticalMoveValidator(),
                LegalPositionValidator()
            )
        )
        )
        val gameState: GameState = GameStateImp(listOf(RectangularBoard(8, 8, emptyMap())),
            CheckMateValidator(),
            ChessTurnValidator(Color.WHITE),
            validator,
            listOf())

        val movement= Movement(Position(0, 0), Position(9, 0))

        for (i in validator) {
            when (val result = i.validate(movement, gameState)) {
                is ValidatorResponse.ValidatorResultValid -> println(result.message)
                is ValidatorResponse.ValidatorResultInvalid -> println(result.message)
            }
        }
    }

    @Test
    fun testOrValidator() {
        val validator: List<Validator> = listOf( OrValidator(
            listOf(
                VerticalMoveValidator(),
                LegalPositionValidator()
            )
        )
        )

        val gameState: GameState = GameStateImp(listOf(RectangularBoard(8, 8, emptyMap())),
            CheckMateValidator(),
            ChessTurnValidator(Color.WHITE),
            validator,
            listOf())

        val movement = Movement(Position(0, 0), Position(9, 0))

        for (i in validator) {
            when (val result = i.validate(movement, gameState)) {
                is ValidatorResponse.ValidatorResultValid -> println(result.message)
                is ValidatorResponse.ValidatorResultInvalid -> println(result.message)
            }
        }
    }
}