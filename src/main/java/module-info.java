module com.example.lol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lol to javafx.fxml;
    exports com.example.lol;
    exports com.example.lol.controllers;
    opens com.example.lol.controllers to javafx.fxml;
}