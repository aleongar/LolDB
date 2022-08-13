package com.example.lol;

import com.example.lol.controllers.IndexController;
import com.example.lol.controllers.LoginController;
import com.example.lol.models.UserDoesNotExistException;
import com.example.lol.models.UserModel;
import com.example.lol.services.JSONService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private void initLoginView(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        ((LoginController)fxmlLoader.getController()).setActualStage(stage);
        stage.show();
        stage.setIconified(false);
        stage.requestFocus();
    }

    private void initIndexView(Stage stage) throws IOException{
        Scene scene;
        try {
            UserModel user = new UserModel(JSONService.getUserFromJSON());
            FXMLLoader fxmlLoader = new FXMLLoader(IndexController.class.getResource("index-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            ((IndexController)fxmlLoader.getController()).initialize(user, stage);
        } catch (IOException | UserDoesNotExistException | JSONException e) {
            System.err.println(e.getMessage());
            initLoginView(stage);
            JSONService.deleteJSONFile();
            return;
        }
        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();
    }

    private boolean existsLoggedUser(){
        File userFile = new File(JSONService.DIR_PATH + JSONService.FILENAME);
        return userFile.exists();
    }

    @Override
    public void start(Stage stage) throws IOException {
        if(existsLoggedUser()){
            initIndexView(stage);
            return;
        }
        initLoginView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}