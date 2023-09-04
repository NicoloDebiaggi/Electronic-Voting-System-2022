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

public class RegistrationController {

    @FXML
    private Button signUpButton;
    @FXML
    private Button regCancelButton;
    @FXML
    private Label regMessageLabel;
    @FXML
    private TextField regUsernameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField regPasswordTextField;
    @FXML
    private PasswordField regConfPasswordTextField;

    private String checkNull = null;

    public void signUpButtonOnAction(ActionEvent e) {
        BaseUserDao dao = new BaseUserDao();
        if (regUsernameTextField.getText().isBlank() == false && regPasswordTextField.getText().isBlank() == false) {
            if (!(regPasswordTextField.getText().equals(regConfPasswordTextField.getText()))) {
                regMessageLabel.setText("Le password non coincidono");
            }
            if (!(validateSignUp())) {
                regMessageLabel.setText("Account già esistente");
            }
        }else {
            regMessageLabel.setText("Inserire le credenziali");
        }
    }

    public void regCancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) regCancelButton.getScene().getWindow();
        stage.close();
    }

    public boolean validateSignUp() {
        BaseUserDao dao = new BaseUserDao();
        if (dao.retrieveUser(regUsernameTextField.getText())==null) {
            String pwGiven = (PwHashing.hashing(regPasswordTextField.getText(), null));
            dao.createUser(new BaseUser(regUsernameTextField.getText(), pwGiven, nameTextField.getText(), lastNameTextField.getText(), 0));
            confirmSignUp();
            return true;
        } else {
            return false;
        }
    }
    public void confirmSignUp() {
        try {
            Parent decRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage regStage = new Stage();
            regStage.initStyle(StageStyle.UNDECORATED);
            regStage.setScene(new Scene(decRoot, 520, 400));
            regStage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

}
