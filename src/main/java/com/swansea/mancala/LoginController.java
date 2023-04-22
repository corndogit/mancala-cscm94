package com.swansea.mancala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for handling logic allowing users to log in and register new accounts.
 * @author Nathan Brenton
 */
public class LoginController {
    public static final int LOGIN_WINDOW_WIDTH = 400;
    public static final int LOGIN_WINDOW_HEIGHT = 300;

    @FXML
    protected PasswordField ConfirmPasswordField;
    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected Label errorMessage;
    @FXML
    protected Button registerViewButton;
    @FXML
    protected Button loginViewButton;

    /**
     * Gets the username and password provided in the login screen and allows the user to log in if they are valid.
     * @param e the event which called this method
     */
    @FXML
    protected void loginButtonAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isValid = validateLoginCredentials(username, password);
        if (isValid) {
            System.out.println("Login successful");
            MainController.loginSuccess = true;
            ((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
        } else {
            errorMessage.setText("Invalid username or password");
        }
    }

    /**
     * Changes the login window between the login and register views.
     * @param e the event which called this method
     * @throws IOException if an IO error occurs
     */
    @FXML
    protected void changeViewButtonAction(ActionEvent e) throws IOException {
        Stage stage;
        FXMLLoader fxmlLoader;

        if (e.getSource() == registerViewButton) {
            stage = (Stage) registerViewButton.getScene().getWindow();
            fxmlLoader = new FXMLLoader(LoginController.class.getResource("register-view.fxml"));
        } else if (e.getSource() == loginViewButton) {
            stage = (Stage) loginViewButton.getScene().getWindow();
            fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        } else {
            throw new IllegalCallerException("invalid FXML id: " + e.getSource());
        }
        Scene scene = new Scene(fxmlLoader.load(), LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Validates the provided credentials against values stored in the database.
     * @param username Provided username
     * @param password Provided password
     * @return True if credentials are valid, otherwise false
     */
    private boolean validateLoginCredentials(String username, String password) {
        // todo: write an algorithm for validating the given credentials
        return username.equals("user") && password.equals("pass");
    }


}
