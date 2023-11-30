package edu.austral.dissis.chess.adapter

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.FinishGameState
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.game.InvalidMoveGameState
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.piece.Color
import edu.austral.dissis.Position
import edu.austral.dissis.chess.game.GameStateImp

class Adapter(private var gameState: GameState) : GameEngine {

    override fun applyMove(move: Move) : MoveResult {
        val parsedMove = parseMove(move)
        return when (val result = gameState.movePiece(parsedMove)){
            is GameStateImp -> { updateState(result) }
            is InvalidMoveGameState -> { InvalidMove(result.errorMessage) }
            is FinishGameState -> { GameOver(parseColor(result.getTurnManager().getTurn())) }
        }
    }

    override fun init(): InitialState {
        return InitialState(getBoardSize(gameState) , getPieces(gameState), getTurn(gameState))
    }

    private fun updateState(gameState: GameState) : MoveResult {
        this.gameState = gameState
        return NewGameState(getPieces(gameState), getTurn(gameState))
    }

    /** Para castear mis piezas a las de la ui */
    private fun getPieces(gameState: GameState) : List<ChessPiece>{
        val positions = gameState.getActualBoard().getOccupiedPositions()
        val chessPieces = mutableListOf<ChessPiece>()
        for (position in positions){
            val piece = gameState.getActualBoard().getPieceByPosition(position)!!
            chessPieces.add(ChessPiece(piece.getId(), parseColor(piece.getColor()), parsePosition(position), piece.getType().toString().lowercase()))

        }
        return chessPieces
    }

    private fun getTurn(gameState: GameState) : PlayerColor {
        return parseColor(gameState.getCurrentTurn())
    }

    private fun getBoardSize(gameState: GameState) : BoardSize {
        val board : Board = gameState.getActualBoard()
        return BoardSize(board.getSizeX(), board.getSizeY())
    }

    private fun parseMove(move: Move) : Movement {
        val from = Position(move.from.row - 1 , move.from.column - 1)
        val to  = Position(move.to.row - 1 , move.to.column -1 )
        return Movement(from, to)
    }

    private fun parseColor(color: Color): PlayerColor {
        return when(color){
            Color.WHITE -> PlayerColor.WHITE
            Color.BLACK -> PlayerColor.BLACK
        }
    }

    private fun parsePosition(position: Position): edu.austral.dissis.chess.gui.Position {
        return edu.austral.dissis.chess.gui.Position(position.row + 1, position.column+1)
    }

}