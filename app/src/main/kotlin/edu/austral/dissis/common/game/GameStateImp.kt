package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.common.piece.Color
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResponse
import edu.austral.dissis.chess.validator.postCondition.PostConditionResult
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.common.validator.TurnValidator
import edu.austral.dissis.common.validator.winCondition.WinCondition

data class GameStateImp (private val boards : List<Board>,
                    private val winCondition: WinCondition,
                    private val turnManager: TurnValidator,
                    private val preConditions: List<Validator>,
                    private val postConditions: List<PostConditionValidator>) : GameState {

    override fun getActualBoard(): Board {
        return boards.last();
    }
    override fun getBoards(): List<Board> {
        return boards;
    }
    override fun getCurrentTurn(): Color {
        return turnManager.getTurn();
    }
    override fun getTurnManager(): TurnValidator {
        return turnManager;
    }
    override fun getPreConditions(): List<Validator> {
        return preConditions;
    }
    override fun getPostConditions(): List<PostConditionValidator> {
        return postConditions;
    }
    override fun getWinCondition(): WinCondition {
        return winCondition;
    }

    // Valido turno, movimientos, preConditions, postConditions, winConditions, incremento moveCounter
    override fun movePiece(movement: Movement): GameState {
        val turnResponse : ValidatorResponse = validateTurn(movement)
        if ( turnResponse is ValidatorResponse.ValidatorResultInvalid)          return invalidMove(turnResponse)

        val pieceMoveResponse : ValidatorResponse = validatePieceMove(movement)
        if (pieceMoveResponse is ValidatorResponse.ValidatorResultInvalid)      return invalidMove(pieceMoveResponse)

        val preConditionResponse : ValidatorResponse = validatePreConditions(movement)
        if (preConditionResponse is ValidatorResponse.ValidatorResultInvalid)   return invalidMove(preConditionResponse)

        val boardAux: Board = this.getActualBoard().update(movement)
        val postConditionResponse : PostConditionResult = validatePostConditions(boardAux)
        val gamePostConditions : GameState = updateGameStateAfterPostConditions(postConditionResponse,boardAux)

        if (getWinCondition().isWin(gamePostConditions))  return finishedGame(gamePostConditions)

        val piece : Piece = this.getActualBoard().getPieceByPosition(movement.from)!!
        piece.incrementMoveCounter()

        return GameStateImp(gamePostConditions.getBoards(), this.getWinCondition(), this.getTurnManager().nextTurn(gamePostConditions), this.getPreConditions(), this.getPostConditions())
    }

    private fun validateTurn(movement: Movement): ValidatorResponse {
        return turnManager.validateTurn(movement, this)
    }

    private fun invalidMove(response: ValidatorResponse.ValidatorResultInvalid): GameState {
        return InvalidMoveGameState(this, response.message)
    }

    private fun validatePreConditions(movement: Movement): ValidatorResponse {
        for (preCondition in getPreConditions()) {
            val result = preCondition.validate(movement, this)
            if (result is ValidatorResponse.ValidatorResultInvalid) {
                return result
            }
        }
        return ValidatorResponse.ValidatorResultValid("OK")
    }


    private fun validatePieceMove(movement: Movement): ValidatorResponse {
        val piece = getActualBoard().getPieceByPosition(movement.from) ?: return ValidatorResponse.ValidatorResultInvalid("No hay una pieza en esta posición para mover")
        return piece.validateMove(movement, this)
    }


    private fun validatePostConditions( board : Board): PostConditionResult {
        var boardAux : Board = board
        for (postCondition in getPostConditions()){
            when (val postConditionResponse : PostConditionResult = postCondition.validate( this, boardAux)) {
                is PostConditionResult.ResultValid -> boardAux = postConditionResponse.board
                is PostConditionResult.ResultInvalid -> continue
            }
        }
        return PostConditionResult.ResultValid(boardAux)
    }

    private fun updateGameStateAfterPostConditions(postConditionResponse: PostConditionResult, boardAux: Board): GameState {
        return if (postConditionResponse is PostConditionResult.ResultValid) {
            GameStateImp(this.getBoards().toMutableList().apply { add(postConditionResponse.board) },
                this.getWinCondition(),
                this.getTurnManager(),
                this.getPreConditions(),
                this.getPostConditions())
        } else {
            GameStateImp(this.getBoards().toMutableList().apply { add(boardAux) },
                this.getWinCondition(),
                this.getTurnManager(),
                this.getPreConditions(),
                this.getPostConditions())
        }
    }

    private fun finishedGame(gamePostConditions: GameState): GameState {
        return FinishGameState(gamePostConditions.getBoards(),
            gamePostConditions.getWinCondition(),
            gamePostConditions.getTurnManager(),
            gamePostConditions.getPreConditions(),
            gamePostConditions.getPostConditions())
    }

}