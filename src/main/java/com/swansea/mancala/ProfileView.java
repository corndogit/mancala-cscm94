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

    public void initialize() {
        openProfile(MainView.loggedInUser);  // todo: may break viewing other profiles in the future
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
