package com.swansea.mancala;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LeaderboardView {
    @FXML
    protected TableView<User> table;  // todo: parameterize with User database objects
    @FXML
    protected TableColumn<User, String> userName;
    @FXML
    protected TableColumn<User, String> gamesPlayed;
    @FXML
    protected TableColumn<User, String> winPercentage;

    @FXML
    protected void updateRows() {

    }

    public void initialize() {
        // make connection
        // make query
        updateRows();
    }

    public void addUserToTable(User user) {

    }
}
