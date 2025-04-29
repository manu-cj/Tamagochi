module tamagochi {
    requires javafx.controls;
    requires javafx.fxml;

    opens tamagochi to javafx.fxml;
    exports tamagochi;
}
