package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SelectProfilePictureView {
    @FXML
    protected Label selectIndicator;
    @FXML
    protected Parent root;
    private String selection;

    /**
     * Confirms the selection and updates the relevant database entry if necessary
     */
    @FXML
    protected void confirmSelection(MouseEvent event) {
        if (selection != null) {
            try {
                DatabaseConnector db = DatabaseConnector.create();
                db.updateProfilePicture(selection, MainView.loggedInUser);
                MainView.loggedInUser.setProfilePicture(selection);
            } catch (SQLException e) {
                e.printStackTrace();
                AlertFactory.createAlert(AlertType.ERROR, "Error", "Database error", e.getMessage()).showAndWait();
            }
        }
        Button buttonSource = ((Button) event.getSource());
        Stage window = (Stage) buttonSource.getScene().getWindow();
        window.close();
    }

    /**
     * Gets the path of the selected image.
     * @param event Event that was fired
     */
    @FXML
    protected void selectPicture(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        selection = String.format("%s.png", source.getId());
        selectIndicator.setText(selection + " selected.");
        selectIndicator.setTextFill(Color.GREEN);
    }
}
