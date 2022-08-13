package com.example.lol.controllers;

import com.example.lol.services.DBService;
import com.example.lol.models.TeamModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeamController {
    @FXML
    private Button modifyButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField shortNameTextField;

    @FXML
    private TextField nationTextField;

    private Stage thisStage;

    public void initialize(TeamModel team, boolean admin, Stage stage){
        thisStage = stage;
        modifyButton.setVisible(admin);
        nameTextField.setEditable(admin);
        nationTextField.setEditable(admin);
        nameTextField.setText(team.getNombre());
        shortNameTextField.setText(team.getId());
        nationTextField.setText(team.getNacion());
    }

    public void initialize(boolean adding, Stage stage){
        thisStage = stage;
        modifyButton.setVisible(adding);
        modifyButton.setText("Añadir");
        nameTextField.setEditable(adding);
        shortNameTextField.setEditable(adding);
        nationTextField.setEditable(adding);
    }

    @FXML
    protected void modifyTeam(){
        System.out.println("Boton modificar");
        if(modifyButton.getText().compareTo("Añadir") == 0){
            DBService.addTeam(nameTextField.getText(), shortNameTextField.getText(), nationTextField.getText());
            thisStage.close();
        }
        if(modifyButton.getText().compareTo("Modificar") == 0){
            DBService.updateTeam(shortNameTextField.getText(), nameTextField.getText(), nationTextField.getText());
            thisStage.close();
        }
    }
}
