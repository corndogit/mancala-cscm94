package com.swansea.mancala;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * It also provides the controller for the main view that is displayed when Mancala is opened.
 * @author Nathan Brenton
 * @version 1.0
 */
public class MainView{
    public static User loggedInUser;
    @FXML
    protected VBox mainWindow;

    /**
     * Opens the login screen in a new window. On successful login, the main menu is entered.
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

    /**
     * Changes the view to the main menu of Mancala
     *
     * @param mainWindow the Stage containing the main window
     */
    public void enterMainMenu(Stage mainWindow) throws IOException {
        FXMLLoader mainMenuLoader = new FXMLLoader(MainView.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(
                mainMenuLoader.load(),
                Main.WINDOW_WIDTH,
                Main.WINDOW_HEIGHT
        );
        mainWindow.setScene(scene);
        mainWindow.show();
    }
}
