package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Controller for handling logic behind the profile view. Can connect to the database to update
 * displayed information and allows access to the view to update the user's profile picture.
 * @author Nathan Brenton
 */
public class ProfileView {
    @FXML
    protected Label playerFullName;
    @FXML
    protected ImageView profilePicture;
    @FXML
    protected Button changeProfilePicture;
    @FXML
    protected Label playerUsername;
    @FXML
    protected Label dateLastLogin;
    @FXML
    protected Label gamesPlayed;
    @FXML
    protected Label winRate;
    private User loadedUser;

    /**
     * Called when this object is instantiated
     */
    public void initialize() {
        if (loadedUser == null) {
            loadedUser = MainView.loggedInUser;
        }
        openProfile(loadedUser);  // todo: may break viewing other profiles in the future
    }

    /**
     * Opens the profile of the provided User.
     * @param user the user to view
     */
    public void openProfile(User user) {
        loadedUser = user;
        playerFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        URL path = null;
        if (user.getProfilePicture() != null) {
            path = ProfileView.class.getResource("assets/profile_pictures/" + user.getProfilePicture());
        }
        if (path == null) {
            path = ProfileView.class.getResource("assets/profile_pictures/default.png");
        }
        assert path != null;
        profilePicture.setImage(new Image(path.toString()));
        playerUsername.setText(user.getUserName());
        dateLastLogin.setText("Date last logged in: " + user.getLoginDate().toString());
        gamesPlayed.setText("Games played: " + user.getNoOfGamesPlayed());
        winRate.setText(String.format("Win percentage: %.2f%%", user.getWinPc()));
    }

    /**
     * Refreshes the profile view by reloading the profile from database
     */
    @FXML
    protected void refreshProfileButton() {
        openProfile(loadedUser);
    }

    /**
     * Opens a new window for selecting the player's profile picture. Update's the player's profile with the
     * new profile picture.
     */
    @FXML
    protected void setProfilePicture() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("select-profile-picture-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Change profile picture");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        openProfile(loadedUser);
    }

    /**
     * Closes the current window.
     * @param event Event that was fired
     */
    @FXML
    protected void closeWindow(MouseEvent event) {
        Button buttonSource = ((Button) event.getSource());
        Stage window = (Stage) buttonSource.getScene().getWindow();
        window.close();
    }
}
