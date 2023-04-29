package com.swansea.mancala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Under construction");
                alert.setHeaderText("Coming soon!");
                alert.setContentText("This feature is yet to be implemented.");
                alert.showAndWait();
            }
        }
    }

    private void quitApplication(Stage mainWindow) {
        // todo: maybe add a confirmation prompt here
        mainWindow.close();
    }

    private void showInNewWindow(String title, String resourcePath) throws IOException {
        Stage stage = new Stage();
        FXMLLoader howToPlayScene = new FXMLLoader(getClass().getResource(resourcePath));
        Scene scene = new Scene(howToPlayScene.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
