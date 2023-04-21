package com.swansea.mancala;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

public class MainMenuController {
    @FXML
    protected Button profileButton;
    @FXML
    protected Button playGameButton;
    @FXML
    protected Button howToPlayButton;
    @FXML
    protected Button settingsButton;
    @FXML
    protected Button quitButton;

    @FXML
    protected void mainMenuButtonClick(ActionEvent e) throws IOException {
        Button buttonSource = ((Button) e.getSource());
        Stage window = (Stage) buttonSource.getScene().getWindow();

        switch (buttonSource.getId()){
            case "playGameButton" -> GameController.startGame(window);
            case "quitButton" -> quitApplication(window);
            case "howToPlayButton" -> showHowToPlay();
            default -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Coming soon!");
                alert.setContentText("This feature is yet to be implemented.");
                alert.showAndWait();
            }
        }
    }

    private void quitApplication(Stage mainWindow) {
        // todo: maybe add a confirmation prompt here
        mainWindow.close();
    }

    private void showHowToPlay() throws IOException {
        Stage stage = new Stage();
        FXMLLoader howToPlayScene = new FXMLLoader(MainMenuController.class.getResource("how-to-play-view.fxml"));
        Scene scene = new Scene(howToPlayScene.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }
}
