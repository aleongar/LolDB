package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.ChampionModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private Button newButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView championImage;

    @FXML
    private CheckBox editionCheck;

    private boolean editing;
    private ResultSet result;
    private ChampionModel champ;
    private ChampionModel oldChamp;
    private int rows;
    private boolean admin;


    public void initialize(ResultSet result, boolean admin){
        this.result = result;
        this.admin = admin;
        editing = false;
        try {
            result.next();
            champ = new ChampionModel(result.getString(1),
                result.getString(2), result.getString(3));
            if(admin) {
                editionCheck.setVisible(true);
                oldChamp = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
                newButton.setVisible(true);
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

    @FXML
    private void enableEditionAbility(){
        editing = editionCheck.isSelected();
        abQTextField.setEditable(editionCheck.isSelected());
        abWTextField.setEditable(editionCheck.isSelected());
        abETextField.setEditable(editionCheck.isSelected());
        abRTextField.setEditable(editionCheck.isSelected());
    }

    private void changeEdition(boolean state){
        nameTextField.setEditable(state);
        dmgTextField.setEditable(state);
        abQTextField.setEditable(state);
        abWTextField.setEditable(state);
        abETextField.setEditable(state);
        abRTextField.setEditable(state);
    }

    private void clear(){
        nameTextField.clear();
        dmgTextField.clear();
        abQTextField.clear();
        abWTextField.clear();
        abETextField.clear();
        abRTextField.clear();
    }

    @FXML
    protected void nextChamp(){
        if(admin)
            checkModifed();
        if(!editing) {
            try {
                result.setFetchDirection(ResultSet.FETCH_FORWARD);
                result.next();
                champ = new ChampionModel(result.getString(1),
                        result.getString(2), result.getString(3));
                if (admin)
                    oldChamp = new ChampionModel(result.getString(1),
                            result.getString(2), result.getString(3));
                if (result.getRow() == rows)
                    nextButton.setDisable(true);
                prevButton.setDisable(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        editing = false;
        nameTextField.setText(champ.getName());
        dmgTextField.setText(champ.getDano());
        abQTextField.setText(champ.getHabilidades()[0]);
        abWTextField.setText(champ.getHabilidades()[1]);
        abETextField.setText(champ.getHabilidades()[2]);
        abRTextField.setText(champ.getHabilidades()[3]);
    }

    @FXML
    protected void prevChamp(){
        if (admin)
            checkModifed();
        if(!editing) {
            try {
                result.setFetchDirection(ResultSet.FETCH_REVERSE);
                result.previous();
                champ = new ChampionModel(result.getString(1),
                        result.getString(2), result.getString(3));
                if (admin)
                    oldChamp = new ChampionModel(result.getString(1),
                            result.getString(2), result.getString(3));
                if (result.getRow() == 1)
                    prevButton.setDisable(true);
                nextButton.setDisable(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        editing = false;
        nameTextField.setText(champ.getName());
        dmgTextField.setText(champ.getDano());
        abQTextField.setText(champ.getHabilidades()[0]);
        abWTextField.setText(champ.getHabilidades()[1]);
        abETextField.setText(champ.getHabilidades()[2]);
        abRTextField.setText(champ.getHabilidades()[3]);
    }

    private void checkModifed(){
        if(abQTextField.getText().compareTo(oldChamp.getHabilidades()[0]) != 0 ||
                abWTextField.getText().compareTo(oldChamp.getHabilidades()[1]) != 0 ||
                abETextField.getText().compareTo(oldChamp.getHabilidades()[2]) != 0 ||
                abRTextField.getText().compareTo(oldChamp.getHabilidades()[3]) != 0 ){
            DDBB.updateChamps(abQTextField.getText(), abWTextField.getText(),
                    abETextField.getText(), abRTextField.getText(), nameTextField.getText());
            try {
               result = DDBB.getChampionQuery();
               result.last();
               nextButton.setDisable(true);
               prevButton.setDisable(false);
                champ = new ChampionModel(result.getString(1),
                        result.getString(2), result.getString(3));
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
    @FXML
    private void newChamp(){
        changeEdition(true);
        clear();
        newButton.setDisable(true);
        insertButton.setVisible(true);
        cancelButton.setVisible(true);
        prevButton.setDisable(true);
        nextButton.setDisable(true);
        editionCheck.setSelected(false);
        editionCheck.setDisable(true);
    }

    @FXML
    private void insertChamp(){
        DDBB.insertChamp(nameTextField.getText(), abQTextField.getText(), abWTextField.getText(),
                abETextField.getText(), abRTextField.getText(), dmgTextField.getText());
        newButton.setDisable(false);
        insertButton.setVisible(false);
        changeEdition(false);
        try {
            result = DDBB.getChampionQuery();
            result.last();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        prevButton.setDisable(false);
        rows = DDBB.getChampionsCount();
    }

    @FXML
    private void cancelInsert(){
        newButton.setDisable(false);
        cancelButton.setVisible(false);
        insertButton.setVisible(false);
        nextButton.setDisable(false);
        changeEdition(false);
        editionCheck.setDisable(false);
        try {
            result.first();
            champ = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
            oldChamp = new ChampionModel(result.getString(1),
                    result.getString(2), result.getString(3));
        }catch (SQLException e){
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
