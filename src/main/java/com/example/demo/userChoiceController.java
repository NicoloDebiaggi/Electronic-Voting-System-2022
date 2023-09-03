package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.ExecutionException;

public class userChoiceController {
    @FXML
    private Button elButton;
    @FXML
    private Button adButton;

    public void adButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) adButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("loginAdmin.fxml"));
            Stage regStage = new Stage();
            regStage.initStyle(StageStyle.UNDECORATED);
            regStage.setScene(new Scene(newRoot, 520, 400));
            regStage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void elButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) elButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage regStage = new Stage();
            regStage.initStyle(StageStyle.UNDECORATED);
            regStage.setScene(new Scene(newRoot, 520, 400));
            regStage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}
