module newcards {
    requires javafx.controls;
    requires javafx.fxml;

    opens newcards to javafx.fxml;
    exports newcards;
}
