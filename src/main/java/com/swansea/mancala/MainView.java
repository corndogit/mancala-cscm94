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
 * The main class for starting and rendering the application. It also provides the
 * controller for the main view that is displayed when Mancala is opened.
 * @author Nathan Brenton
 * @version 1.0
 */
public class MainView extends Application {
    public static User loggedInUser;
    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGHT = 600;
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
     * @param mainWindow the Stage containing the main window
     */
    public void enterMainMenu(Stage mainWindow) throws IOException {
        FXMLLoader mainMenuLoader = new FXMLLoader(MainView.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(
                mainMenuLoader.load(),
                WINDOW_WIDTH,
                WINDOW_HEIGHT
        );
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    /**
     * Used for starting the application. Loads the relevant FXML files and renders them in a new window.
     * @param stage used internally by JavaFX on launch
     * @throws IOException if an error occurs during input or output operations
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Mancala");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
