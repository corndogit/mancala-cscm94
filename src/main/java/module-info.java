module com.swansea.mancala {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.swansea.mancala to javafx.fxml;
    exports com.swansea.mancala;
}