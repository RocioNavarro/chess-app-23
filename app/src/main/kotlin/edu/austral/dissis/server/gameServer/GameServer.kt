package edu.austral.dissis.server.gameServer

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.common.game.FinishGameState
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.common.game.GameStateImp
import edu.austral.dissis.common.game.InvalidMoveGameState
import edu.austral.dissis.common.movement.Movement
import edu.austral.dissis.server.payload.GameStatePayload
import edu.austral.dissis.server.payload.InitializePayload
import edu.austral.dissis.server.listener.InitializeListener
import edu.austral.dissis.server.listener.MovementListener
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder


/** Le paso el juego y el builder del server */
class GameServer(private var gameState: GameState,
                 private val builder: ServerBuilder = NettyServerBuilder.createDefault() ) {

    private val server : Server

    init {
        server = initializeServer()
        server.start()
    }

    fun getGameState() : GameState {
        return gameState
    }

    /** Le dice al cliente que tiene que inicializar su juego con la info del payload (tamaño del tablero, piezas, turno) */
    fun sendInitialize(clientID: String, payload: InitializePayload){
        server.sendMessage(clientID, Message("initialize", payload))
    }

    /** Se llama cuando se mueve una pieza
     *  A partir del resultado del movimineto, manda a todos los clientes suscriptos (broadcast)
     *  el mensaje con el payload para que actualicen su UI */
    fun broadcastState(payload: GameStatePayload){
        when ( payload ) {
            is GameStatePayload.SuccessfulMovePayload -> server.broadcast(Message("successfulMove", payload))
            is GameStatePayload.InvalidMovePayload -> server.broadcast(Message("invalidMove", payload))
            is GameStatePayload.FinishGamePayload -> server.broadcast(Message("finishedGame", payload))
        }
    }

    fun applyMove(movement: Movement) : GameState {
        return when (val result = gameState.movePiece(movement)) {
            is FinishGameState, is InvalidMoveGameState -> result
            is GameStateImp -> {
                this.gameState = result
                result
            }
        }
    }

    private fun initializeServer(): Server {
        return builder
            .withPort(9300)
            .withConnectionListener(InitializeListener(this)) /** asignamos lo que queremos hacer cuando alguien se conecta */
            .addMessageListener("move", object: TypeReference<Message<Movement>>(){}, MovementListener(this)) /** suscribe a los listeners */
            .build() /** devuelve el server */
    }

}