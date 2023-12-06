package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.validator.ValidatorResponse

/** Usamos el checkValidator para tener acá la union con el state */
class IsNotCheckValidator : edu.austral.dissis.common.validator.Validator {

    private val checkValidator = CheckValidator()

    override fun validate(movement: Movement, gameState: GameState): ValidatorResponse {
        val newGameState = simulateMove(movement, gameState) /** simulo el movimiento */
        return if (checkValidator.validateCheck(newGameState, gameState.getCurrentTurn())) { /** me fijo si me deja al actual turno en check */
            ValidatorResponse.ValidatorResultInvalid("Estás en jaque!")
        } else {
            ValidatorResponse.ValidatorResultValid("No estás en jaque")
        }
    }


    private fun simulateMove(movement: Movement, gameState: GameState): GameState {
        val newBoard = gameState.getActualBoard().update(movement)
        val newBoards = gameState.getBoards().toMutableList()
        newBoards.add(newBoard)
        return GameStateImp(
            newBoards,
            gameState.getWinCondition(),
            gameState.getTurnManager(),
            gameState.getPreConditions(),
            gameState.getPostConditions()
        )
    }
}