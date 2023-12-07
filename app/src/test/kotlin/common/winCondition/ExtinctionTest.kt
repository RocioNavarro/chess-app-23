package common.winCondition

import edu.austral.dissis.checkers.factory.createCheckersNormalGame
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.validator.winCondition.ExtinctionCondition
import edu.austral.dissis.common.validator.winCondition.WinCondition
import org.junit.Test

class ExtinctionTest {
    private val initialGameState: GameState = createCheckersNormalGame()
    private val extinctionValidator: WinCondition = ExtinctionCondition()

    @Test
    fun notFinishedGame() {
        assert(!extinctionValidator.isWin(initialGameState))
    }
}