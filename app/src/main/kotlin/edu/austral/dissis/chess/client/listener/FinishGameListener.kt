package edu.austral.dissis.chess.client.listener

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.payload.GameStatePayload
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class FinishGameListener(private val root: GameView) : MessageListener<GameStatePayload.FinishGamePayload> {

    override fun handleMessage(message: Message<GameStatePayload.FinishGamePayload>) {
        root.handleMoveResult(GameOver(message.payload.winner))
    }

}