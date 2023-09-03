package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class resCatController {
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel; //=elezione.id
    @FXML
    private VBox vertVBox;
    @FXML
    private Label blankLabel; //= elezione.blank
    @FXML
    private Label totLabel; //=elezione.tot

    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void newButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) newButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("ChooseSchedaAdmin.fxml"));
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(new Scene(newRoot, 520, 400));
            newStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    /*
    public void resElection(elezione.id) {
        for(int i=0;i<NumeroCandidati;i++) {
            Label lb = new Label(elezione.candidati[i] + elezione.votopercandidato[i]); //meglio fare una mappa e sortarla decrescentemente in base ai voti
            lb.setLayoutX(0);
            lb.setLayoutY(i*100);
            vertVBox.getChildren().add(lb);
        }
    }*/


}
