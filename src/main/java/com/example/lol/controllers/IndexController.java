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

    public void initialize(UserModel user){
        userLabel.setText(makeUser(user.getUsername(), user.getId()));
        this.user = user;
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
        Stage stage = new Stage();
        stage.setTitle("Champs");
        stage.setScene(scene);
        if(!stage.isShowing())
            stage.show();
        stage.setIconified(false);
        stage.requestFocus();
    }

    @FXML
    protected void openProplayersView(){
        System.out.println("Faker what was that");
    }
    @FXML
    protected void openTeamsView(){
        System.out.println("No sé que decir sobre los equipos, la verdad");
    }

    @FXML
    protected void openItemsView(){
        System.out.println("Sinceramente me parecen una mierda los de ahora pero tengo que vivir con ello");
    }
}
