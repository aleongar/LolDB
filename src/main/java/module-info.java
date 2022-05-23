module com.example.lol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lol to javafx.fxml;
    exports com.example.lol;
}