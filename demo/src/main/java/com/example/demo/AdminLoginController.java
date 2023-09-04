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
        ModUserDao dao = new ModUserDao();
        adMessageLabel.setText("");
        if(adUsernameTextField.getText().isBlank()==false && adPasswordTextField.getText().isBlank()==false) {
            if(validateLogin()&&(dao.retrieveUser(adUsernameTextField.getText())!=null)) {
                confirmLogin();
            } else {
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
        return dao.retrieveUser(adUsernameTextField.getText()).checkPassword(adPasswordTextField.getText());
    }
    public void confirmLogin() {
        try {
            Stage stage = (Stage) adLogButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("adminHub.fxml"));
            Stage lStage = new Stage();
            lStage.initStyle(StageStyle.UNDECORATED);
            lStage.setScene(new Scene(newRoot, 520, 400));
            lStage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}
