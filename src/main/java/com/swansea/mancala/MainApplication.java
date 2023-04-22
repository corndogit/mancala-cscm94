package com.swansea.mancala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for starting and rendering the application.
 * @author Nathan Brenton
 */
public class MainApplication extends Application {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;

    /**
     * Used for starting the application. Loads the relevant FXML files and renders them in a new window.
     * @param stage used internally by JavaFX on launch
     * @throws IOException if an error occurs during input or output operations
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
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