package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
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
    public void startGame(Stage stage, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("game-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                width,
                height
        );
        stage.setScene(scene);
        stage.show();
    }

    public void storeResultInDB(User[] players, String usernameOfWinner) {
        System.out.println("Storing the results in database...");
        String player1 = players[0].getUserName();
        String player2 = players[1].getUserName();
        boolean player2IsGuest = "P2".equals(player2);

        try {
            DatabaseConnector db = DatabaseConnector.create();
            if (player1.equals(usernameOfWinner)) {
                // p1 wins++ and games++, p2 games++
                db.updateUserWins(player1);
                if (!player2IsGuest) {
                    db.updateUserGames(player2);
                }
            } else if ("tie".equals(usernameOfWinner)) {
                // games++ only for both
                db.updateUserGames(player1);
                if (!player2IsGuest) {
                    db.updateUserGames(player2);
                }
            } else {
                // p2 wins++ and games++, p1 games++
                db.updateUserGames(player1);
                if (!player2IsGuest) {
                    db.updateUserWins(player2);
                }
            }
            MainView.loggedInUser = db.getUserByUsername(player1);
        } catch (SQLException e) {
            AlertFactory.createAlert(
                    AlertType.ERROR,
                    "Error",
                    "Database error",
                    "Sorry, your score could not be saved."
            ).showAndWait();
        }
    }


    /**
     * Presents the user with a confirmation box upon clicking the "exit game" button.
     * @param e Event fired by the button
     */
    @FXML
    public void exitConfirmation(MouseEvent e) throws IOException {
        Stage mainWindow = (Stage) ((Button) e.getSource()).getScene().getWindow();
        Alert alert = AlertFactory.createAlert(
                AlertType.CONFIRMATION,
                "Exit game",
                "Exit game",
                "Are you sure you want to exit?"
        );
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent()) {
            if (ButtonType.OK.equals(option.get())) {
                MainView mainMenu = new MainView();
                mainMenu.enterMainMenu(mainWindow);
            }
        }
    }
}
