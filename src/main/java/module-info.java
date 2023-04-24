module com.swansea.mancala {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.swansea.mancala to javafx.fxml;
    exports com.swansea.mancala;
}