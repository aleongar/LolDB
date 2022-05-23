package com.example.lol;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    protected void signIn(){
        System.out.println(userTextField.getText() + " " + passTextField.getText());
    }
}
