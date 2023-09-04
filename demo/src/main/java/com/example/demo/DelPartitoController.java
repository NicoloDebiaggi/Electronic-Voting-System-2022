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

public class DelPartitoController {

    @FXML
    private Button removeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private Button backButton;
    @FXML
    private Label alertLabel;
    @FXML
    private Label confLabel;

    public void removeButtonOnAction(ActionEvent e) {
        alertLabel.setText("");
        confLabel.setText("");
        PartitoDao dao = new PartitoDao();
        if(dao.retrievePartito(idTextField.getText())!=null) {
            dao.deletePartito(idTextField.getText());
            alertLabel.setText("");
            confLabel.setText("PARTITO ELIMINATO");

        } else {
            confLabel.setText("");
            alertLabel.setText("PARTITO NON ESISTENTE");
        }
        alertLabel.setText("");
        confLabel.setText("PROSSIMA INSERZIONE");
    }
    public void backButtonOnAction(ActionEvent e) {
        try {
            Stage estage = (Stage) backButton.getScene().getWindow();
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
