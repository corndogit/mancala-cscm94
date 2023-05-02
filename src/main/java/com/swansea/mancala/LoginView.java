package com.swansea.mancala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Class for handling logic allowing users to log in and register new accounts.
 * @author Nathan Brenton
 */
public class LoginView {
    public static final int LOGIN_WINDOW_WIDTH = 400;
    public static final int LOGIN_WINDOW_HEIGHT = 300;
    private final String ERROR_MESSAGE = "Invalid %s.";
    @FXML
    protected TextField firstNameField;
    @FXML
    protected TextField lastNameField;
    @FXML
    protected PasswordField confirmPasswordField;
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
    protected void loginButtonAction(ActionEvent e) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isValid = validateLoginCredentials(username, password);
        if (isValid) {
            DatabaseConnector db = DatabaseConnector.create();
            try {
                MainView.loggedInUser = db.getUserByUsername(username);
            } catch (SQLException ex) {
                System.out.println(ex.getSQLState());
            }
            ((Stage) (((Button) e.getSource()).getScene().getWindow())).close();  // close window
        } else {
            errorMessage.setText(String.format(ERROR_MESSAGE, "username or password"));
        }
    }

    /**
     * Changes the login window between the login and register views.
     * @param e the event which called this method
     */
    @FXML
    protected void changeViewButtonAction(ActionEvent e) throws IOException {
        Stage stage;
        FXMLLoader fxmlLoader;

        if (e.getSource() == registerViewButton) {
            stage = (Stage) registerViewButton.getScene().getWindow();
            fxmlLoader = new FXMLLoader(LoginView.class.getResource("register-view.fxml"));
        } else if (e.getSource() == loginViewButton) {
            stage = (Stage) loginViewButton.getScene().getWindow();
            fxmlLoader = new FXMLLoader(LoginView.class.getResource("login-view.fxml"));
        } else {
            throw new IllegalCallerException("invalid FXML id: " + e.getSource());
        }
        Scene scene = new Scene(fxmlLoader.load(), LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void registerNewUserButtonAction() throws SQLException {
        // validation
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(username);
        System.out.println(password);

        if ("".equals(firstName)) {
            errorMessage.setText(String.format(ERROR_MESSAGE, "first name"));
            return;
        }
        System.out.println();
        if ("".equals(lastName)) {
            errorMessage.setText(String.format(ERROR_MESSAGE, "last name"));
            return;
        }
        if ("".equals(username)) {
            errorMessage.setText(String.format(ERROR_MESSAGE, "username"));
            return;
        }
        if ("".equals(password) || "".equals(confirmPassword)) {
            errorMessage.setText(String.format(ERROR_MESSAGE, "password"));
            return;
        }
        if (!password.equals(confirmPassword)) {
            errorMessage.setText("Passwords do not match.");
            return;
        }

        // connect to database
        DatabaseConnector databaseConnector = DatabaseConnector.create();
        if (databaseConnector.userExistsInDatabase(username)) {
            errorMessage.setText("Username is taken.");
            return;
        }
        User user = new User(firstName, lastName, username, password);
        databaseConnector.createUser(user);
        AlertFactory.createAlert(
                Alert.AlertType.INFORMATION,
                "Success!",
                "Account registered",
                "Please try logging in with your new account details."
        ).showAndWait();
        errorMessage.setText("");
    }

    /**
     * Validates the provided credentials against values stored in the database.
     * @param username Provided username
     * @param password Provided password
     * @return True if credentials are valid, otherwise false
     */
    private boolean validateLoginCredentials(String username, String password) throws SQLException {
        DatabaseConnector connection = DatabaseConnector.create();
        return connection.validateLogin(username, password);
    }
}
