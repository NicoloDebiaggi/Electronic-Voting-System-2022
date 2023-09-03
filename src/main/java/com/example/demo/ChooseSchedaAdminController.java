package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChooseSchedaAdminController {
    private boolean checkElection;
    @FXML
    private Button manageButton;
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void manageButtonOnAction(ActionEvent e){
        try {
            Stage stage = (Stage) manageButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("manageElection.fxml"));
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(new Scene(newRoot, 520, 400));
            newStage.show();
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    public void newButtonOnAction(ActionEvent e) {
        try {
            if (!checkElection) {
                    checkElection=true;
                    Stage stage = (Stage) newButton.getScene().getWindow();
                    stage.close();
                    Parent newRoot = FXMLLoader.load(getClass().getResource("ChooseSchedaAdmin.fxml"));
                    Stage newStage = new Stage();
                    newStage.initStyle(StageStyle.UNDECORATED);
                    newStage.setScene(new Scene(newRoot, 520, 400));
                    newStage.show();
            }else{
                manageButtonOnAction(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
}