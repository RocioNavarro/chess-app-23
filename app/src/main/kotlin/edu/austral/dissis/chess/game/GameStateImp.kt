package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.chess.validator.Validator
import edu.austral.dissis.chess.validator.ValidatorResponse
import edu.austral.dissis.chess.validator.postCondition.PostConditionResult
import edu.austral.dissis.chess.validator.postCondition.PostConditionValidator
import edu.austral.dissis.chess.validator.preCondition.turnCondition.TurnValidator
import edu.austral.dissis.chess.validator.winCondition.WinCondition

class GameStateImp (private val boards : List<Board>,
                    private val winCondition: WinCondition,
                    private val turnManager: TurnValidator,
                    private val preConditions: List<Validator>,
                    private val postConditions: List<PostConditionValidator>) : GameState{
    override fun getActualBoard(): Board {
        return boards.last();
    }

    override fun getBoards(): List<Board> {
        return boards;
    }

    override fun getCurrentTurn(): Color {
        return turnManager.getTurn();
    }

    override fun movePiece(movement: Movement): GameState {
        // Valido turno
        val turnResponse : ValidatorResponse = getTurnManager().validateTurn(movement, this)
        if ( turnResponse is ValidatorResponse.ValidatorResultInvalid) {
            return InvalidMoveGameState(this.getBoards(),
                this.getWinCondition(),
                this.getTurnManager(),
                this.getPreConditions(),
                this.getPostConditions(),
                turnResponse.message)
        }
        // Valido precondiciones
        val preConditionResponse : ValidatorResponse = validatePreConditions(movement)
        if (preConditionResponse is ValidatorResponse.ValidatorResultInvalid) {
            return InvalidMoveGameState(this.getBoards(),
                this.getWinCondition(),
                this.getTurnManager(),
                this.getPreConditions(),
                this.getPostConditions(),
                preConditionResponse.message)
        }
        // Valido movimientos
        val pieceMoveResponse : ValidatorResponse = validatePieceMove(movement)
        if (pieceMoveResponse is ValidatorResponse.ValidatorResultInvalid) {
            return InvalidMoveGameState(this.getBoards(),
                this.getWinCondition(),
                this.getTurnManager(),
                this.getPreConditions(),
                this.getPostConditions(),
                pieceMoveResponse.message)
        }
        // Valido postCondiciones
        val boardAux: Board = this.getActualBoard().update(movement)

        val postConditionResponse : PostConditionResult = validatePostConditions(boardAux)
        val boardAfterPostConditions = if( postConditionResponse is PostConditionResult.ResultValid) postConditionResponse.board else boardAux

        val gameAuxBoards = this.getBoards().toMutableList()
        gameAuxBoards.add(boardAfterPostConditions)

        val gameAux = GameStateImp(gameAuxBoards,
            this.getWinCondition(),
            this.getTurnManager(),
            this.getPreConditions(),
            this.getPostConditions())

        // Valido winConditions
        if (getWinCondition().isWin(gameAux)) {
            return FinishGameState(gameAux.getBoards(),
                gameAux.getWinCondition(),
                gameAux.getTurnManager(),
                gameAux.getPreConditions(),
                gameAux.getPostConditions())
        }

        return GameStateImp(gameAuxBoards,
            this.getWinCondition(),
            this.getTurnManager().nextTurn(),
            this.getPreConditions(),
            this.getPostConditions())
    }

    private fun validatePreConditions(movement: Movement): ValidatorResponse {
        for (preCondition in getPreConditions()) {
            when (val preConditionResponse : ValidatorResponse = preCondition.validate(movement, this)) {
                is ValidatorResponse.ValidatorResultInvalid -> return preConditionResponse
                is ValidatorResponse.ValidatorResultValid -> continue
            }
        }
        return ValidatorResponse.ValidatorResultValid("Se cumplen todas las precondiciones")
    }

    private fun validatePieceMove(movement: Movement): ValidatorResponse {
        val piece = getActualBoard().getPieceByPosition(movement.from) ?: return ValidatorResponse.ValidatorResultInvalid("No hay una pieza en esta posicion para mover")
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
}