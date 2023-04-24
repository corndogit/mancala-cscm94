package com.swansea.mancala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Game class serves as an instance of a Mancala game played by two players. Provides functionality for the Game.
 * @author Nathan Brenton
 */
public class GameController {
    // todo: add exit button (may be able to save game before exit too?)
    protected String[] backgrounds = new String[]{  // todo: add background customization option
            "default",
            "background-1.png"
    };

    /**
     * Used from outside the class to start the game.
     * @throws IOException if an IO error occurs.
     */
    public static void startGame(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("game-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                MainApplication.WINDOW_WIDTH,
                MainApplication.WINDOW_HEIGHT
        );
        scene.getRoot().setStyle("-fx-background-image: url(assets/background-1.png);");
        stage.setScene(scene);
        stage.show();
    }

}
