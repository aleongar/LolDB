package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.PlayerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayersController {
    @FXML
    private Button versionButton;

    @FXML
    private Button newPlayerButton;

    @FXML
    private Button addPlayerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

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

    @FXML
    private Label warningLabel;


    private PlayerModel oldPlayer;
    private ArrayList<PlayerModel> players;
    private boolean admin;
    private boolean lastVerView;
    private boolean adding;

    public void initialize(boolean admin){
        this.admin = admin;
        lastVerView = false;
        adding = false;
        players = DDBB.getNewerPlayersView();
        updatePlayers();
        if(admin) {
            newPlayerButton.setVisible(true);
            deleteButton.setVisible(true);
        }
    }

    private void updatePlayers(){
        clearInputs();
        playersListView.getItems().clear();
        for(PlayerModel p : players){
            playersListView.getItems().add(p.getUsername());
        }
    }

    private void clearInputs(){
        nameTextField.clear();
        surnameTextField.clear();
        playernameTextField.clear();
        teamTextField.clear();
        bestchampTextField.clear();
        masteryTextField.clear();
        versionTextField.clear();
        warningLabel.setText("");
        playersListView.getSelectionModel().clearSelection();
        deleteButton.setDisable(true);
    }

    private void setWriteableLabels(){
        nameTextField.setEditable(adding);
        surnameTextField.setEditable(adding);
        playernameTextField.setEditable(adding);
        teamTextField.setEditable(adding);
        bestchampTextField.setEditable(adding);
        masteryTextField.setEditable(adding);
        deleteButton.setDisable(!adding);
    }

    @FXML
    protected void selectPlayer(){
        int index = playersListView.getSelectionModel().getSelectedIndex();
        if(index != -1) {
            PlayerModel playerSelected = players.get(index);
            nameTextField.setText(playerSelected.getName());
            surnameTextField.setText(playerSelected.getSurname());
            playernameTextField.setText(playerSelected.getUsername());
            teamTextField.setText(playerSelected.getTeam());
            bestchampTextField.setText(playerSelected.getBestChamp());
            masteryTextField.setText(playerSelected.getMastery());
            versionTextField.setText(playerSelected.getVersion());
            if(!lastVerView)
                deleteButton.setDisable(false);
        }
    }

    @FXML
    protected void changeVersion(){
        System.out.println("version change");
        clearInputs();
        if(lastVerView) {
            players = DDBB.getNewerPlayersView();
            versionButton.setText("Versión Anterior");
            lastVerView = false;
            updatePlayers();
            newPlayerButton.setDisable(false);
        }
        else {
            players = DDBB.getOlderPlayer();
            versionButton.setText("Versión Actual");
            lastVerView = true;
            updatePlayers();
            newPlayerButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @FXML
    protected void newPlayer(){
        if(!lastVerView) {
            clearInputs();
            adding = true;
            setWriteableLabels();
            versionButton.setDisable(true);
            addPlayerButton.setDisable(false);
            addPlayerButton.setVisible(true);
            cancelButton.setDisable(false);
            cancelButton.setVisible(true);
            newPlayerButton.setDisable(true);
            playersListView.setDisable(true);
        } else {
            warningLabel.setText("Lista antigua, no puedes añadir jugadores");
        }
    }

    @FXML
    protected void addPlayer(){
        String result;
        result = DDBB.insertPlayer(nameTextField.getText(), surnameTextField.getText(),
                playernameTextField.getText(), teamTextField.getText(), masteryTextField.getText(),
                bestchampTextField.getText());
        if(result.compareTo("Jugador añadido") == 0){
            warningLabel.setTextFill(Color.GREEN);
            warningLabel.setText(result);
        }else{
            warningLabel.setTextFill(Color.RED);
            warningLabel.setText(result);
        }
        adding = false;
        setWriteableLabels();
        versionButton.setDisable(false);
        addPlayerButton.setDisable(true);
        addPlayerButton.setVisible(false);
        cancelButton.setDisable(true);
        cancelButton.setVisible(false);
        newPlayerButton.setDisable(false);
        players = DDBB.getNewerPlayersView();
        playersListView.setDisable(false);
        updatePlayers();
    }

    @FXML
    protected void cancel(){
        adding = false;
        setWriteableLabels();
        versionButton.setDisable(false);
        addPlayerButton.setDisable(true);
        addPlayerButton.setVisible(false);
        cancelButton.setDisable(true);
        cancelButton.setVisible(false);
        newPlayerButton.setDisable(false);
        playersListView.setDisable(false);
        clearInputs();
    }

    @FXML
    protected void deletePlayer(){
        System.out.println("delete");
        DDBB.deletePlayer(playernameTextField.getText());
        players = DDBB.getNewerPlayersView();
        updatePlayers();
    }
}
