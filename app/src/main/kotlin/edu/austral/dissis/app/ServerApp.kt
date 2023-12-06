package edu.austral.dissis.app

import edu.austral.dissis.chess.factory.GameFactory.Companion.createChessClassicGame
import edu.austral.dissis.server.gameServer.GameServer

fun main() {
    GameServer(createChessClassicGame())
    // TODO  GameServer(createChessCapaBlancaGame())
}