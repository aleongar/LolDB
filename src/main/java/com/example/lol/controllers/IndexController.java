package com.example.lol.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IndexController {

    @FXML
    private Label userLabel;

    public void initialize(String name, int id){
        userLabel.setText(makeUser(name, id));
    }

    private String makeUser(String name, int id){
        return name + ":" + id;
    }

    @FXML
    protected void openChampionsView(){
        System.out.println("Teemo no es un campeón, es una mierda con forma de tejón");
    }
}
