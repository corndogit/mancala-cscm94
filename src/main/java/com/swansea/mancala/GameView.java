package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * The Game class serves as an instance of a Mancala game played by two players. Provides functionality for the Game.
 * @author Nathan Brenton
 */
public class GameView {
    @FXML
    protected Parent BoardView;
    @FXML
    protected BoardView BoardViewController;

    /**
     * Called when the game is instantiated
     */
    public void initialize() {
        BoardViewController.setGameController(this);  // pass a reference for this class to the board
    }

    /**
     * Creates a new game instance and opens it in the current window.
     */
    public void startGame(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("game-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                MainApplication.WINDOW_WIDTH,
                MainApplication.WINDOW_HEIGHT
        );
        stage.setScene(scene);
        stage.show();
    }

    public void storeResultInDB(String[] players, int[] scores) {
        System.out.println("Storing the results in database...");
        // todo: add logic here when possible
    }


    /**
     * Presents the user with a confirmation box upon clicking the "exit game" button.
     * @param e Event fired by the button
     */
    @FXML
    public void exitConfirmation(MouseEvent e) throws IOException {
        Stage mainWindow = (Stage) ((Button) e.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("Exit game");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType>option = alert.showAndWait();
        if (option.isPresent()) {
            if (ButtonType.OK.equals(option.get())) {
                MainView mainMenu = new MainView();
                mainMenu.enterMainMenu(mainWindow);
            }
        }
    }
}
