package edu.austral.dissis.app

import edu.austral.dissis.GAME_TYPE
import edu.austral.dissis.GameType
import edu.austral.dissis.checkers.factory.createCheckersNormalGame
import edu.austral.dissis.chess.factory.createChessCapablancaGame
import edu.austral.dissis.chess.factory.createChessNormalGame
import edu.austral.dissis.common.game.GameState
import edu.austral.dissis.server.gameServer.GameServer

fun main() {
    GameServer(createGame()) /** Creo la instancia del server y el GameServer lo levanta */
}

private fun createGame(): GameState {
    return when(GAME_TYPE){
        GameType.CHECKERS -> createCheckersNormalGame()
        GameType.CHESS -> createChessNormalGame()
        GameType.CAPABLANCA -> createChessCapablancaGame()
    }
}