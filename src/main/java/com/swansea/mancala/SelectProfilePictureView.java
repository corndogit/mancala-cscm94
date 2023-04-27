package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectProfilePictureView {
    @FXML
    protected VBox root;
    private String selectionPath;

    /**
     * Confirms the selection and updates the relevant database entry if necessary
     */
    @FXML
    protected void confirmSelection() {
        if (selectionPath != null) {
            // todo: send edit query to database and change profile picture path
        }

        // close the window
        ((Stage) root.getScene().getWindow()).close();
    }

    /**
     * Gets the path of the selected image.
     * @param event Source of the event
     */
    @FXML
    protected void selectPicture(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        selectionPath = source.getImage().getUrl();
    }

    public String getSelectionPath() {
        return selectionPath;
    }
}
