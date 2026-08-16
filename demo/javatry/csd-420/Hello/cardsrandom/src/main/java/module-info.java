module cards {
    requires javafx.controls;
    requires javafx.fxml;

    opens cards to javafx.fxml;
    exports cards;
}
