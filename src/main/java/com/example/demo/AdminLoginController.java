package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;

public class AdminLoginController {
    @FXML
    private  Button adLogButton;
    @FXML
    private TextField adUsernameTextField;
    @FXML
    private PasswordField adPasswordTextField;
    @FXML
    private Label adMessageLabel;
    @FXML
    private Button adCancButton;

    public void loginButtonOnAction(ActionEvent e) {

        if(adUsernameTextField.getText().isBlank()==false && adPasswordTextField.getText().isBlank()==false) {
            if(!(validateLogin())) {
                adMessageLabel.setText("Credenziali errate");
            }
        }else {
            adMessageLabel.setText("Inserire le credenziali");
        }
    }

    public void adCancButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) adCancButton.getScene().getWindow();
        stage.close();
    }

    public boolean validateLogin() {
        ModUserDao dao = new ModUserDao();
        String checkPassword = dao.retrieveUser(adUsernameTextField.getText()).getPassword();
        if(adPasswordTextField.getText().equals(checkPassword)) {
            confirmLogin();
            return true;
        } else {
            return false;
        }
    }
    public void confirmLogin() {
        try {
            Stage stage = (Stage) adLogButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("ChooseSchedaAdmin.fxml"));
            Stage adStage = new Stage();
            adStage.initStyle(StageStyle.UNDECORATED);
            adStage.setScene(new Scene(newRoot, 520, 400));
            adStage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}
