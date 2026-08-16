module cardsselect {
    requires javafx.controls;
    requires javafx.fxml;

    opens cardsselect to javafx.fxml;
    exports cardsselect;
}
