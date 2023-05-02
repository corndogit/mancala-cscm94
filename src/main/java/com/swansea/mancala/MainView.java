package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {
    static User loggedInUser;
    @FXML
    protected VBox mainWindow;

    /**
     * Opens the login screen in a new window.
     * @throws IOException if an IO error occurs
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        FXMLLoader loginViewLoader = new FXMLLoader(MainView.class.getResource("login-view.fxml"));
        Scene scene = new Scene(
                loginViewLoader.load(),
                LoginView.LOGIN_WINDOW_WIDTH,
                LoginView.LOGIN_WINDOW_HEIGHT
        );
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);  // makes the main window unclickable
        stage.showAndWait();
        if (loggedInUser != null) {
            enterMainMenu((Stage) mainWindow.getScene().getWindow());
        }
    }

    public void enterMainMenu(Stage mainWindow) throws IOException {
        FXMLLoader mainMenuLoader = new FXMLLoader(MainView.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(
                mainMenuLoader.load(),
                MainApplication.WINDOW_WIDTH,
                MainApplication.WINDOW_HEIGHT
        );
        mainWindow.setScene(scene);
        mainWindow.show();
    }
}