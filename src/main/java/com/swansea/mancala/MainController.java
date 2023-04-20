package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    /**
     * Opens the login screen in a new window.
     * @throws IOException if an IO error occurs
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        FXMLLoader loginViewLoader = new FXMLLoader(MainController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(
                loginViewLoader.load(),
                LoginController.LOGIN_WINDOW_WIDTH,
                LoginController.LOGIN_WINDOW_HEIGHT
        );
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);  // makes the main window unclickable
        stage.showAndWait();
    }
}