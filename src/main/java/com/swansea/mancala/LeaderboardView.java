package com.swansea.mancala;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class for handling the display of leaderboard information. Handles data processing which is displayed
 * using the leaderboard view.
 * @author Nathan Brenton
 */
public class LeaderboardView {
    @FXML
    protected TableView<User> table = new TableView<>();
    @FXML
    protected TableColumn<User, String> userRank;
    @FXML
    protected TableColumn<User, String> userName;
    @FXML
    protected TableColumn<User, String> gamesPlayed;
    @FXML
    protected TableColumn<User, String> winPercentage;

    /**
     * Called when this object is instantiated.
     */
    public void initialize() {
        // set columns
        userRank.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(data.getValue().getRank().toString()));
        userName.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(data.getValue().getUserName()));
        gamesPlayed.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(String.valueOf(data.getValue().getNoOfGamesPlayed())));
        winPercentage.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(floatToPercentage(data.getValue().getWinPc())));

        // make connection
        ArrayList<User> users;
        try {
            DatabaseConnector db = DatabaseConnector.create();
            users = db.getLeaderboard();
        } catch (SQLException e) {
            AlertFactory.createAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Database error",
                    "Score data could not be loaded."
            ).showAndWait();
            return;
        }
        table.setItems(FXCollections.observableArrayList(users));
    }

    /**
     * Convert a float value to a formatted percentage
     * @param value a float
     * @return "[value]%" rounded to 2 decimal places
     */
    private String floatToPercentage(float value) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(value) + "%";
    }
}
