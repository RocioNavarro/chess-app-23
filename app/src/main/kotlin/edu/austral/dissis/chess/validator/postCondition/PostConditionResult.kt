package edu.austral.dissis.chess.validator.postCondition

import edu.austral.dissis.chess.board.Board

sealed interface PostConditionResult {
    data class ResultValid(val board: Board) : PostConditionResult
    data class ResultInvalid(val message: String) : PostConditionResult
}