package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.GameStateImp
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse

// Usamos el checkValidator porque es mas facil negarlo que checkear si en todas las jugadas no queda en jaque
// Es una preCondition del game
class IsNotCheckValidator : Validator {

    private val checkValidator : CheckValidator = CheckValidator()

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {

        val boardAux : Board = gameState.getActualBoard().update(movement)
        val gameAuxBoards = gameState.getBoards().toMutableList()
        gameAuxBoards.add(boardAux)
        val gameAux = GameStateImp(gameAuxBoards,
            gameState.getWinCondition(),
            gameState.getTurnManager(),
            gameState.getPreConditions(),
            gameState.getPostConditions())
        return if ( checkValidator.validate(gameAux) ) {
            ValidatorResponse.ValidatorResultInvalid("El rey queda en jaque")
        } else {
            ValidatorResponse.ValidatorResultValid("El rey no queda en jaque")
        }
    }
}