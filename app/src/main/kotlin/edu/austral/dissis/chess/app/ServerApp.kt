package edu.austral.dissis.chess.app

import edu.austral.dissis.chess.factory.GameFactory.Companion.createChessClassicGame
import edu.austral.dissis.chess.server.gameServer.GameServer

fun main() {
    GameServer(createChessClassicGame())
    // TODO  GameServer(createChessCapaBlancaGame())
}