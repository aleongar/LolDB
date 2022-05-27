package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.PlayerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class PlayersController {
    @FXML
    private Button versionButton;

    @FXML
    private TextField versionTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField playernameTextField;

    @FXML
    private TextField teamTextField;

    @FXML
    private TextField bestchampTextField;

    @FXML
    private TextField masteryTextField;

    @FXML
    private ListView playersListView;


    private PlayerModel oldPlayer;
    private ArrayList<PlayerModel> players;
    private boolean admin;
    private boolean lastVerView;

    public void initialize(boolean admin){
        this.admin = admin;
        lastVerView = false;
        players = DDBB.getNewerPlayersView();
        for(PlayerModel p : players){
            playersListView.getItems().add(p.getUsername());
        }
    }

    @FXML
    protected void selectPlayer(){
        int index =playersListView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            PlayerModel playerSelected = players.get(index);
            nameTextField.setText(playerSelected.getName());
            surnameTextField.setText(playerSelected.getSurname());
            playernameTextField.setText(playerSelected.getUsername());
            teamTextField.setText(playerSelected.getTeam());
            bestchampTextField.setText(playerSelected.getBestChamp());
            masteryTextField.setText(Integer.toString(playerSelected.getMastery()));
        }
    }

    @FXML
    protected void changeVersion(){
        System.out.println("version change");
    }

}
