package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.TeamModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TeamsController {
    private boolean admin;
    private ArrayList<TeamModel> teams;
    private Stage teamStage;
    private int index;

    public void initialize(boolean admin){
        this.admin = admin;
        getTeamsFromDB();
        addButton.setVisible(admin);
        deleteButton.setVisible(admin);
    }

    private void getTeamsFromDB(){
        teamsListView.getItems().clear();
        teams = DDBB.getTeams();
        for(TeamModel team : teams){
            teamsListView.getItems().add(team.getNombre());
        }
    }

    @FXML
    private ListView teamsListView;

    @FXML
    private Button viewButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    protected void addTeam(){
        System.out.println("Adding");
        teamStage = new Stage();
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TeamController.class.getResource("team-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            ((TeamController) fxmlLoader.getController())
                    .initialize(true, teamStage);
            teamStage.setScene(scene);
            teamStage.show();
            viewButton.setDisable(true);
            teamStage.setOnHidden((windowEvent) -> {
                getTeamsFromDB();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void removeTeam(){
        System.out.println("Removing");
        TeamModel selectedTeam = teams.get(index);
        DDBB.deleteTeam(selectedTeam.getNombre(), selectedTeam.getId(), selectedTeam.getNacion());
        getTeamsFromDB();
    }

    @FXML
    protected void viewTeam(){
        teamStage = new Stage();
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TeamController.class.getResource("team-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            ((TeamController) fxmlLoader.getController())
                    .initialize(teams.get(teamsListView.getSelectionModel().getSelectedIndex()), admin, teamStage);
            teamStage.setScene(scene);
            teamStage.show();
            viewButton.setDisable(true);
            teamStage.setOnHidden((windowEvent) -> {
                getTeamsFromDB();
            });
            teamStage = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void selectTeam() {
        if (teamsListView.getSelectionModel().getSelectedItem() != null) {
            index = teamsListView.getSelectionModel().getSelectedIndex();
            viewButton.setDisable(false);
            deleteButton.setDisable(false);
            teamsListView.setOnMouseClicked((mouseEvent) -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    viewButton.setDisable(false);
                    deleteButton.setDisable(false);
                    if (mouseEvent.getClickCount() == 2) {
                        teamStage = new Stage();
                        Scene scene;
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(TeamController.class.getResource("team-view.fxml"));
                            scene = new Scene(fxmlLoader.load());
                            ((TeamController) fxmlLoader.getController())
                                    .initialize(teams.get(teamsListView.getSelectionModel().getSelectedIndex()), admin, teamStage);
                            teamStage.setScene(scene);
                            teamStage.show();
                            teamStage.setOnHidden((windowEvent) -> {
                                viewButton.setDisable(true);
                                getTeamsFromDB();
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    System.out.println("No has seleccionado un equipo");
                }
            });
        }
    }

}