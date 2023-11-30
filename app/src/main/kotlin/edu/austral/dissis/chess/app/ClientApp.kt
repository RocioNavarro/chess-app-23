package edu.austral.dissis.chess.app

import edu.austral.dissis.chess.client.GameClient
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

    fun main() {
        Application.launch(GameUI::class.java)
    }

    class GameUI() : Application() {
        private val imageResolver = CachedImageResolver(DefaultImageResolver())

        companion object {
            const val title = "Chess Game"
            val gameClient = GameClient()
        }

        override fun start(primaryStage: Stage) {
            primaryStage.title = title

            val root = GameView(imageResolver)
            primaryStage.scene = Scene(root)

            gameClient.start(root)

            primaryStage.show()
        }
    }
