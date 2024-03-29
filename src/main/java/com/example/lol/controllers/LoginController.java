package com.example.lol.controllers;

import com.example.lol.services.DBService;
import com.example.lol.models.UserModel;
import com.example.lol.services.JSONService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Scene scene;
    private Stage actualStage;
    private Stage signUpStage;
    private Stage resetPasswordStage;
    public void initialize(){
        signUpStage = new Stage();
        resetPasswordStage = new Stage();
    }

    public void setActualStage(Stage actualStage) {
        this.actualStage = actualStage;
    }

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private Label warningLabel;
    @FXML
    private CheckBox keepLoged;

    @FXML
    protected void signIn(){
        int id = DBService.login(userTextField.getText(), UserModel.hash(passTextField.getText()));
        if(id == DBService.NULL_ID){
            warningLabel.setVisible(true);
            return;
        }
        UserModel user = DBService.userLogged(userTextField.getText(), UserModel.hash(passTextField.getText()));
        assert user != null;
        if(keepLoged.isSelected())
            JSONService.createJSONFile(user);

        FXMLLoader fxmlLoader = new FXMLLoader(IndexController.class.getResource("index-view.fxml"));
        Stage stage = new Stage();
        try {
            scene = new Scene(fxmlLoader.load());
            ((IndexController)fxmlLoader.getController()).initialize(user, stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();
        actualStage.close();
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

    @FXML
    protected void passReset(){
        FXMLLoader fxmlLoader = new FXMLLoader(ResetPassController.class.getResource("reset-pass-view.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resetPasswordStage.setTitle("Cambiar contraseña");
        resetPasswordStage.setScene(scene);
        if(!resetPasswordStage.isShowing())
            resetPasswordStage.show();
        resetPasswordStage.setIconified(false);
        resetPasswordStage.requestFocus();
    }
}
