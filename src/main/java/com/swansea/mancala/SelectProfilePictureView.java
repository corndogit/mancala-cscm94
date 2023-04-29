package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectProfilePictureView {
    @FXML
    protected Parent root;
    private String selection;

    /**
     * Confirms the selection and updates the relevant database entry if necessary
     */
    @FXML
    protected void confirmSelection(MouseEvent event) {
        if (selection != null) {
            System.out.println(selection + " selected");
            // todo: send edit query to database and change profile picture path
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
        selection = String.format("assets/%s.png", source.getId());
    }
}
