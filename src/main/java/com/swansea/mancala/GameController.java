package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Provides functionality for the Game class.
 * @author
 */
public class GameController {
    protected String[] backgrounds = new String[]{
            "default",
            "background-1.png"
    };

    /**
     * Used from outside the class to start the game.
     * @throws IOException if an IO error occurs.
     */
    public static void startGame(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getRoot().setStyle("-fx-background-image: url(assets/background-1.png);");
        stage.setScene(scene);
        stage.show();
    }
}
