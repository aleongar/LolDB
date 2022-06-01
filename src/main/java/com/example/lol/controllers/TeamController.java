package com.example.lol.controllers;

import com.example.lol.models.TeamModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TeamController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField shortNameTextField;

    @FXML
    private TextField nationTextField;

    public void initialize(TeamModel team, boolean admin){
        if(admin)
            System.out.println("Button de eliminar visible");
        nameTextField.setText(team.getNombre());
        shortNameTextField.setText(team.getId());
        nationTextField.setText(team.getNacion());
    }
}
