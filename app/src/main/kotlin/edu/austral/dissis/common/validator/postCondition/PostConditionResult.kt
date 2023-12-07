package edu.austral.dissis.common.validator.postCondition

import edu.austral.dissis.common.board.Board

sealed interface PostConditionResult {
    data class ResultValid(val board: Board) : PostConditionResult
    data class ResultInvalid(val message: String) : PostConditionResult
}