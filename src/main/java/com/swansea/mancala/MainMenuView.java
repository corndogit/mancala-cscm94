package com.swansea.mancala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class MainMenuView {
    @FXML
    protected Button profileButton;
    @FXML
    protected Button playGameButton;
    @FXML
    protected Button howToPlayButton;
    @FXML
    protected Button leaderboardButton;
    @FXML
    protected Button settingsButton;
    @FXML
    protected Button quitButton;

    /**
     * Determines which button on the main menu was clicked and calls the appropriate method.
     * @param e event that was fired
     */
    @FXML
    protected void mainMenuButtonClick(ActionEvent e) throws IOException {
        Button buttonSource = ((Button) e.getSource());
        Stage window = (Stage) buttonSource.getScene().getWindow();

        switch (buttonSource.getId()){
            case "playGameButton" -> {
                GameView game = new GameView();
                game.startGame(window);
            }
            case "quitButton" -> quitApplication(window);
            case "howToPlayButton" -> showInNewWindow("How to play", "how-to-play-view.fxml");
            case "profileButton" -> showInNewWindow("Profile", "profile-view.fxml");
            case "leaderboardButton" -> showInNewWindow("Leadboard", "leaderboard-view.fxml");
            default -> {
                Alert alert = AlertFactory.createAlert(
                        AlertType.INFORMATION,
                        "Under construction",
                        "Coming soon!",
                        "This feature is yet to be implemented."
                );
                alert.showAndWait();
            }
        }
    }

    /**
     * Closes the provided stage.
     * @param mainWindow The stage that is closed
     */
    private void quitApplication(Stage mainWindow) {
        Alert alert = AlertFactory.createAlert(
                AlertType.CONFIRMATION,
                "Exit Mancala",
                "Exit Mancala",
                "Are you sure you want to exit?"
        );
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent()) {
            if (ButtonType.OK.equals(option.get())) {
                mainWindow.close();
            }
        }
    }

    /**
     * Retrieves an FXML view and opens it in a new window.
     * @param title New window title
     * @param resourcePath Path to the FXML view
     */
    private void showInNewWindow(String title, String resourcePath) throws IOException {
        Stage stage = new Stage();
        FXMLLoader howToPlayScene = new FXMLLoader(getClass().getResource(resourcePath));
        Scene scene = new Scene(howToPlayScene.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
