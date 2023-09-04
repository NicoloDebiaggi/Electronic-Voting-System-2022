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

public class CandidatoInPartitoController {
    @FXML
    private Button addButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField cfTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private Label alertLabel;
    @FXML
    private Button removeButton;
    @FXML
    private Label confLabel;


    public void removeButtonOnAction(ActionEvent e) {
        //remove candidato dal partito tramite utilizzo di cfTextField.getText();
        //uso alertLabel se non è già presente
        alertLabel.setText("");
        PartitoDao pdao = new PartitoDao();
        String[] can = {};
        boolean flag=false;
        if(pdao.retrievePartito(idTextField.getText())==null) {
            alertLabel.setText("PARTITO NON ESISTENTE");
        } else {
            Partito par = pdao.retrievePartito(idTextField.getText());
            CandidatoDao cdao = new CandidatoDao();
            confLabel.setText("");
            if (cdao.retrieveCandidato(cfTextField.getText()) != null) {
                if (par != null) {
                    can = par.getCandidati();
                    for (int i = 0; i < can.length; i++) {
                        if (cfTextField.getText().equals(can[i])) {
                            pdao.rimuoviCandidato(cfTextField.getText(), idTextField.getText());
                            alertLabel.setText("");
                            confLabel.setText("CANDIDATO RIMOSSO");
                            break;
                        }else {
                            confLabel.setText("");
                            alertLabel.setText("CANDIDATO NON PRESENTE");
                        }
                    }
                }
            }
            alertLabel.setText("");
            confLabel.setText("PROSSIMA INSERZIONE");
        }
    }

    public void addButtonOnAction(ActionEvent e) {
        //add candidato al partito tramite utilizzo di cfTextField.getText();
        //uso alertLabel se c'è già
        PartitoDao pdao = new PartitoDao();
        String[] can = {};
        boolean flag=false;
        CandidatoDao cdao = new CandidatoDao();
        Partito par = pdao.retrievePartito(idTextField.getText());
        alertLabel.setText("");
        if(cdao.retrieveCandidato(cfTextField.getText()) != null && par!=null) {
            can = par.getCandidati();
            for(int i=0;i<can.length;i++) {
                if(cfTextField.getText().equals(can[i])) {
                    flag = true;
                    break;
                }
            }if(!flag) {
                pdao.aggiungiCandidato(cfTextField.getText(), idTextField.getText());
                alertLabel.setText("");
                confLabel.setText("CANDIDATO INSERITO");

            } else {
                confLabel.setText("");
                alertLabel.setText("CANDIDATO GIA' PRESENTE");
            }
            alertLabel.setText("");
            confLabel.setText("PROSSIMA INSERZIONE");
        }
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
