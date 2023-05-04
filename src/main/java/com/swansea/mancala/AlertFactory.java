package com.swansea.mancala;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Factory class for easily creating and instantiating Alert dialog boxes.
 * @author Nathan Brenton
 */
public class AlertFactory {
    /**
     * Create a new alert dialog box.
     * @param type The AlertType for this alert
     * @param title The window title
     * @param header The alert header
     * @param context The description text
     * @return an Alert object
     */
    public static Alert createAlert(AlertType type, String title, String header, String context) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert;
    }
}
