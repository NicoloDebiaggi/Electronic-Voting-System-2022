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

public class StartController {
    @FXML
    private Button loginButton;
    @FXML
    private Button regButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e) {
        loginMessageLabel.setText("");
        BaseUserDao dao = new BaseUserDao();
        BaseUser bu = null;
        if(usernameTextField.getText().isBlank()==false && passwordPasswordField.getText().isBlank()==false) {
            if((validateLogin()) && (dao.retrieveUser(usernameTextField.getText())!=null)){
                bu.setInstance(dao.retrieveUser(usernameTextField.getText()));
                confirmLogin();
            } else {
                loginMessageLabel.setText("Credenziali errate");
            }
        }else {
            loginMessageLabel.setText("Inserire le credenziali");
        }
    }
    public void regButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) regButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("registrazione.fxml"));
            Stage regStage = new Stage();
            regStage.initStyle(StageStyle.UNDECORATED);
            regStage.setScene(new Scene(newRoot, 520, 400));
            regStage.show();

        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public boolean validateLogin() {
        /*BaseUserDao dao = new BaseUserDao();
        String pwTrue = dao.retrieveUser(usernameTextField.getText()).getPassword();
        String salt = pwTrue.substring(pwTrue.length()-32);
        String pwGiven = (PwHashing.hashing(passwordPasswordField.getText(), salt));
        if (pwTrue.equals(pwGiven)) {
            confirmLogin();
            return true;
        } else {
            return false;
        }*/
        BaseUserDao dao = new BaseUserDao();
        return dao.retrieveUser(usernameTextField.getText()).checkPassword(passwordPasswordField.getText());

    }
    public void confirmLogin() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("electionChoice.fxml"));
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