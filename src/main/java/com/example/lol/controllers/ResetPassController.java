package com.example.lol.controllers;

import com.example.lol.bussiness.DDBB;
import com.example.lol.models.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetPassController {

     @FXML
     private TextField userTextField;

     @FXML
     private TextField idTextField;

     @FXML
     private PasswordField passTextField;

     @FXML
     private PasswordField repeatTextField;

     @FXML
     private Label warningLabel;

     @FXML
     protected void changePassword(){
          if(DDBB.checkUser(userTextField.getText(), idTextField.getText()) == 0){
               warningLabel.setText("No existe un usuario con ese id");
          } else if (passTextField.getText().compareTo(repeatTextField.getText()) != 0) {
               warningLabel.setText("Las contraseñas no coinciden");
          } else {
               DDBB.updatePassword(userTextField.getText(), idTextField.getText(), UserModel.hash(passTextField.getText()));
               System.out.println(UserModel.hash(passTextField.getText()));
          }
     }

}
