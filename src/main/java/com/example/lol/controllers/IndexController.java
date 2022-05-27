package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.ChampionModel;
import com.example.lol.models.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {

    @FXML
    private Label userLabel;

    private UserModel user;
    private Stage actualStage;
    private Stage championsStage;
    private Stage playerStage;

    public void initialize(UserModel user, Stage actualStage){
        userLabel.setText(makeUser(user.getUsername(), user.getId()));
        this.user = user;
        this.actualStage = actualStage;
        championsStage = new Stage();
        playerStage = new Stage();
    }

    private String makeUser(String name, int id){
        return name + ":" + id;
    }

    @FXML
    protected void openChampionsView(){
        System.out.println("Teemo no es un campeón, es una mierda con forma de tejón");
        FXMLLoader fxmlLoader = new FXMLLoader(ChampionsController.class.getResource("champions-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
            ((ChampionsController)fxmlLoader.getController()).initialize(DDBB.getChampionQuery(), user.isAdmin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        championsStage.setTitle("Champs");
        championsStage.setScene(scene);
        if(!championsStage.isShowing())
            championsStage.show();
        championsStage.setIconified(false);
        championsStage.requestFocus();
    }

    @FXML
    protected void openProplayersView(){
        System.out.println("Faker what was that");
        FXMLLoader fxmlLoader = new FXMLLoader(PlayersController.class.getResource("players-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
            ((PlayersController)fxmlLoader.getController()).initialize(user.isAdmin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        playerStage.setTitle("Players");
        playerStage.setScene(scene);
        if(!playerStage.isShowing())
            playerStage.show();
        playerStage.setIconified(false);
        playerStage.requestFocus();
    }
    @FXML
    protected void openTeamsView(){
        System.out.println("No sé que decir sobre los equipos, la verdad");
    }

    @FXML
    protected void openItemsView(){
        System.out.println("Sinceramente me parecen una mierda los de ahora pero tengo que vivir con ello");
    }

    @FXML
    protected void signOut(){
        System.out.println("Cerrando sesión");
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Scene scene;
        Stage stage = new Stage();
        try {
            scene = new Scene(fxmlLoader.load());
            ((LoginController)fxmlLoader.getController()).setActualStage(stage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Login");
        stage.setScene(scene);
        if(!stage.isShowing())
            stage.show();
        stage.setIconified(false);
        stage.requestFocus();
        championsStage.close();
        playerStage.close();
        actualStage.close();
    }
}
