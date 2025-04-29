module tamagochi {
    requires javafx.controls;
    requires javafx.fxml;

    opens tamagochi.controller to javafx.fxml;
    exports tamagochi.controller;
    
    exports tamagochi;
}

