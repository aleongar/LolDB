package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private PasswordField repeatTextField;

    public void initialize(String username){
        userTextField.setText(username);
    }

    @FXML
    protected void clear(){
        userTextField.clear();
        passTextField.clear();
        repeatTextField.clear();
    }

    @FXML
    protected void signUp(){
        if(passTextField.getText().compareTo(repeatTextField.getText()) != 0){
            System.out.println("The passwords don't match \nLas contraseñas no coinciden");
        } else {
            System.out.println(userTextField.getText());
            System.out.println(UserModel.hash(passTextField.getText()));
            DDBB.signup(userTextField.getText(), UserModel.hash(passTextField.getText()));
        }
    }
}
