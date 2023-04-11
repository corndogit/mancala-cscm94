module com.swansea.mancala {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.swansea.mancala to javafx.fxml;
    exports com.swansea.mancala;
}