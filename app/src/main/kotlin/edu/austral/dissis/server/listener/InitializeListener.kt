package edu.austral.dissis.server.listener

import edu.austral.dissis.common.adapter.Adapter
import edu.austral.dissis.chess.payload.InitializePayload
import edu.austral.dissis.server.gameServer.GameServer
import edu.austral.ingsis.clientserver.ServerConnectionListener

class InitializeListener(private val gameServer: GameServer) : ServerConnectionListener {

    /** Se llama cuando alguien se conecta al servidor */
    override fun handleClientConnection(clientId: String) {
        val currentGameState = gameServer.getGameState()
        val adapterGameState = Adapter(currentGameState).init()
        val payload = InitializePayload(adapterGameState.boardSize, adapterGameState.pieces, adapterGameState.currentPlayer)
        gameServer.sendInitialize(clientId, payload)
    }

    override fun handleClientConnectionClosed(clientId: String) {
        println("Client $clientId disconnected")
    }

}