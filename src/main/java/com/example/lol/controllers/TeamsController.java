package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.TeamModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TeamsController {
    private boolean admin;
    private ArrayList<TeamModel> teams;
    private Stage teamStage;

    public void initialize(boolean admin){
        this.admin = admin;
        teams = DDBB.getTeams();
        for(TeamModel team : teams){
            teamsListView.getItems().add(team.getNombre());
        }
        addButton.setVisible(admin);
        deleteButton.setVisible(admin);
    }

    @FXML
    private ListView teamsListView;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    protected void addTeam(){
        System.out.println("Adding");
    }

    @FXML
    protected void removeTeam(){
        System.out.println("Removing");
    }

    @FXML
    protected void selectTeam(){
        if(teamsListView.getSelectionModel().getSelectedItem() != null){
            teamStage = new Stage();
            Scene scene;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(TeamController.class.getResource("team-view.fxml"));
                scene = new Scene(fxmlLoader.load());
                ((TeamController)fxmlLoader.getController())
                        .initialize(teams.get(teamsListView.getSelectionModel().getSelectedIndex()) ,admin);
                teamStage.setScene(scene);
                teamStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("No has seleccionado un equipo");
        }
    }

}