package com.example.lol.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Scene scene;
    private Stage signUpStage;

    public void initialize(){
        signUpStage = new Stage();
    }

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    protected void signIn(){
        System.out.println(userTextField.getText() + " " + passTextField.getText());
    }

    @FXML
    protected void signUp(){
        FXMLLoader fxmlLoader = new FXMLLoader(SignupController.class.getResource("signup-view.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
            ((SignupController)fxmlLoader.getController()).initialize(userTextField.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        signUpStage.setTitle("SignUp");
        signUpStage.setScene(scene);
        if(!signUpStage.isShowing())
            signUpStage.show();
        signUpStage.setIconified(false);
        signUpStage.requestFocus();
    }
}
