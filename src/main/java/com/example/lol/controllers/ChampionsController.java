package com.example.lol.controllers;

import com.example.lol.models.ChampionModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChampionsController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField dmgTextField;

    @FXML
    private TextField abQTextField;

    @FXML
    private TextField abWTextField;

    @FXML
    private TextField abETextField;

    @FXML
    private TextField abRTextField;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    private ResultSet result;
    private ChampionModel champ;


    public void initialize(ResultSet result){
        this.result = result;
        try {
            result.next();
            champ = new ChampionModel(result.getString(1), result.getString(2), result.getString(3));
        } catch (SQLException e) {
            System.err.println("La consulta no devolvió nada");
            throw new RuntimeException(e);
        }
        nameTextField.setText(champ.getName());
        dmgTextField.setText(champ.getDano());
        abQTextField.setText(champ.getHabilidades()[0]);
        abWTextField.setText(champ.getHabilidades()[1]);
        abETextField.setText(champ.getHabilidades()[2]);
        abRTextField.setText(champ.getHabilidades()[3]);
        prevButton.setDisable(true);
    }

    @FXML
    protected void nextChamp(){
        try {
            result.next();
            champ = new ChampionModel(result.getString(1), result.getString(2), result.getString(3));
            if(!result.next())
                nextButton.setDisable(true);
            prevButton.setDisable(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameTextField.setText(champ.getName());
        dmgTextField.setText(champ.getDano());
        abQTextField.setText(champ.getHabilidades()[0]);
        abWTextField.setText(champ.getHabilidades()[1]);
        abETextField.setText(champ.getHabilidades()[2]);
        abRTextField.setText(champ.getHabilidades()[3]);
    }

    @FXML
    protected void prevChamp(){
        try {
            result.previous();
            champ = new ChampionModel(result.getString(1), result.getString(2), result.getString(3));
            if(!result.previous())
                prevButton.setDisable(true);
            nextButton.setDisable(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameTextField.setText(champ.getName());
        dmgTextField.setText(champ.getDano());
        abQTextField.setText(champ.getHabilidades()[0]);
        abWTextField.setText(champ.getHabilidades()[1]);
        abETextField.setText(champ.getHabilidades()[2]);
        abRTextField.setText(champ.getHabilidades()[3]);
    }

}
