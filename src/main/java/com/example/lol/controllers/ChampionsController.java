package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.ChampionModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView championImage;

    private ResultSet result;
    private ChampionModel champ;
    private ChampionModel oldChamp;
    private int rows;
    private boolean admin;


    public void initialize(ResultSet result, boolean admin){
        this.result = result;
        this.admin = admin;
        try {
            result.next();
            champ = new ChampionModel(result.getString(1),
                result.getString(2), result.getString(3));
            if(admin) {
                enableEdition();
                oldChamp = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
            }
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
        rows = DDBB.getChampionsCount();
        prevButton.setDisable(true);
    }


    private void enableEdition(){
        abQTextField.setEditable(true);
        abWTextField.setEditable(true);
        abETextField.setEditable(true);
        abRTextField.setEditable(true);
    }

    @FXML
    protected void nextChamp(){
        try {
            result.setFetchDirection(ResultSet.FETCH_FORWARD);
            result.next();
            champ = new ChampionModel(result.getString(1),
                result.getString(2), result.getString(3));
            if(admin)
                oldChamp = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
            if(result.getRow() == rows)
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
            result.setFetchDirection(ResultSet.FETCH_REVERSE);
            result.previous();
            champ = new ChampionModel(result.getString(1),
                result.getString(2), result.getString(3));
            if(admin)
                oldChamp = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
            if(result.getRow() == 1)
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

    private void checkModifed(){
        if(!champ.equals(oldChamp)){

        }
    }

}
