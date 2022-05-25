package com.example.lol;

import com.example.lol.controllers.LoginController;
import com.example.lol.controllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        ((LoginController)fxmlLoader.getController()).setActualStage(stage);
        stage.show();
        stage.setIconified(false);
        stage.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}