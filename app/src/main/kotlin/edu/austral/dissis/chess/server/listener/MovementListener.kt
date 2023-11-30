package edu.austral.dissis.chess.server.listener

import edu.austral.dissis.chess.adapter.Adapter
import edu.austral.dissis.chess.game.FinishGameState
import edu.austral.dissis.chess.game.GameState
import edu.austral.dissis.chess.game.GameStateImp
import edu.austral.dissis.chess.game.InvalidMoveGameState
import edu.austral.dissis.chess.movement.Movement
import edu.austral.dissis.chess.payload.GameStatePayload
import edu.austral.dissis.chess.server.gameServer.GameServer
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

/** Este listener se encarga de recibir los movimientos de los clientes y actualizar el estado del juego */
class MovementListener(private val server: GameServer) : MessageListener<Movement> {

    override fun handleMessage(message: Message<Movement>) {
        server.broadcastState( adaptGameState(server.applyMove(message.payload)) )
    }

    private fun adaptGameState(gameState: GameState) : GameStatePayload {
        val adapterGameState = Adapter(gameState).init()
        return when (gameState){
            is GameStateImp -> GameStatePayload.SuccessfulMovePayload(adapterGameState.pieces, adapterGameState.currentPlayer)
            is InvalidMoveGameState -> GameStatePayload.InvalidMovePayload(gameState.errorMessage)
            is FinishGameState -> GameStatePayload.FinishGamePayload(adapterGameState.currentPlayer)
        }
    }

}