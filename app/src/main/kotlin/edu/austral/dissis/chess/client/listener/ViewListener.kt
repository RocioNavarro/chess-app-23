package edu.austral.dissis.chess.client.listener

import edu.austral.dissis.chess.client.GameClient
import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.Position
import edu.austral.dissis.chess.movement.Movement

class ViewListener(private val gameClient: GameClient) : GameEventListener {

    /** Cuando desde la ui se hace un move */
    override fun handleMove(move: Move) {
        gameClient.applyMove(parseMove(move))
    }

    private fun parseMove(move: Move) : Movement {
        val from = Position(move.from.row -1, move.from.column -1)
        val to  = Position(move.to.row -1 , move.to.column -1)
        return Movement(from, to)
    }
}