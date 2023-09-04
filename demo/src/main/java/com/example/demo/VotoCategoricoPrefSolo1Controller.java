package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class VotoCategoricoPrefSolo1Controller implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private ChoiceBox parChoiceBox;
    @FXML
    private ChoiceBox pref1Box;
    @FXML
    private Label alertLabel;

    public void yesAction (ActionEvent e) {
        SchedaCategoricaPrefDao dao = new SchedaCategoricaPrefDao();
        SchedaCategoricaPref sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: "+ parChoiceBox.getValue().toString().toUpperCase() + " con preferenze:"+ pref1Box.getValue().toString().toUpperCase() +"?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            CandidatoDao cdao = new CandidatoDao();
            if (cdao.retrievePartitoId(pref1Box.getValue().toString()).equals(parChoiceBox.getValue().toString())){
                    sr = new SchedaCategoricaPref(1, pref1Box.getValue().toString(), null, parChoiceBox.getValue().toString(), 0);
                    dao.createScheda(sr);
                    BaseUserDao bdao = new BaseUserDao();
                    bdao.setVoted(BaseUser.getInstance().getUsername());
                    try {
                        Stage stage = (Stage) yesButton.getScene().getWindow();
                        stage.close();
                        Parent newRoot = FXMLLoader.load(getClass().getResource("postVoto.fxml"));
                        Stage regStage = new Stage();
                        regStage.initStyle(StageStyle.UNDECORATED);
                        regStage.setScene(new Scene(newRoot, 520, 400));
                        regStage.show();

                    } catch (Exception er) {
                        er.printStackTrace();
                        er.getCause();
                    }
            }else {
                alertLabel.setText("PREFERENZE NON DEL PARTITO SCELTO");
            }
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    public void blankAction (ActionEvent e) {
        SchedaCategoricaPrefDao dao = new SchedaCategoricaPrefDao();
        SchedaCategoricaPref sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: SCHEDA BIANCA?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaCategoricaPref(1, null,null, null,1);
                dao.createScheda(sr);
                BaseUserDao bdao = new BaseUserDao();
                bdao.setVoted(BaseUser.getInstance().getUsername());
                try {
                    Stage stage = (Stage) blankButton.getScene().getWindow();
                    stage.close();
                    Parent newRoot = FXMLLoader.load(getClass().getResource("postVoto.fxml"));
                    Stage regStage = new Stage();
                    regStage.initStyle(StageStyle.UNDECORATED);
                    regStage.setScene(new Scene(newRoot, 520, 400));
                    regStage.show();

                } catch (Exception er) {
                    er.printStackTrace();
                    er.getCause();
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartitoDao dao = new PartitoDao();
        Partito[] par = dao.retrieveAll();
        String[] pars = new String[par.length];
        for(int i=0;i<par.length;i++) {
            pars[i] = par[i].getNomeId();
        }
        parChoiceBox.getItems().addAll(pars);

        CandidatoDao cdao = new CandidatoDao();
        Candidato[] cand = cdao.retrieveAll();
        String[] cands = new String[cand.length];
        for(int j=0; j<cand.length;j++) {
            cands[j] = cand[j].getCf(); //[j].getNome() + " " + cand[j].getCognome();
        }
        pref1Box.getItems().addAll(cands);

    }
}


