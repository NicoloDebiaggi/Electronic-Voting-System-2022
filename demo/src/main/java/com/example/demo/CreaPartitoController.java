package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class CreaPartitoController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nomeTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label alertLabel;
    @FXML
    private Label confLabel;

    public void addButtonOnAction(ActionEvent e) {
        alertLabel.setText("");
        confLabel.setText("");
        PartitoDao dao = new PartitoDao();
        if(dao.retrievePartito(idTextField.getText())==null) {
            dao.createPartito(new Partito(idTextField.getText(), nomeTextField.getText(), null));
            alertLabel.setText("");
            confLabel.setText("INSERIMENTO RIUSCITO");

        } else {
            confLabel.setText("");
            alertLabel.setText("PARTITO GIA' ESISTENTE");
        }
        alertLabel.setText("");
        confLabel.setText("PROSSIMA INSERZIONE");
    }
    public void exitButtonOnAction(ActionEvent e) {
        try {
            Stage estage = (Stage) exitButton.getScene().getWindow();
            estage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("adminHub.fxml"));
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(new Scene(newRoot, 520, 400));
            newStage.show();
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

}
