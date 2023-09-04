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

public class VotoCategoricoPrefController implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private ChoiceBox parChoiceBox;
    @FXML
    private ChoiceBox pref1Box;
    @FXML
    private ChoiceBox pref2Box;
    @FXML
    private Label alertLabel;


    public void yesAction (ActionEvent e) {
        SchedaCategoricaPrefDao dao = new SchedaCategoricaPrefDao();
        SchedaCategoricaPref sr;
        alertLabel.setText("");
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: "+ parChoiceBox.getValue().toString().toUpperCase() + " con preferenze:"+ pref1Box.getValue().toString() + ", "+ pref2Box.getValue().toString() +"?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                CandidatoDao cdao = new CandidatoDao();
                if ((cdao.retrievePartitoId(pref1Box.getValue().toString()).equals(parChoiceBox.getValue().toString())) && (cdao.retrievePartitoId(pref2Box.getValue().toString()).equals(parChoiceBox.getValue().toString()))) {

                    if ((!(pref1Box.getValue().toString().equals(pref2Box.getValue().toString()))) || (cdao.retrieveCandidato(pref1Box.getValue().toString()).getSesso().equals(cdao.retrieveCandidato(pref2Box.getValue().toString()).getSesso()))) {
                        sr = new SchedaCategoricaPref(0, pref1Box.getValue().toString(), pref2Box.getValue().toString(), parChoiceBox.getValue().toString(), 0);
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
                    }else{
                        alertLabel.setText("UGUALE IDENTITA' O SESSO");
                    }
                }else {
                    alertLabel.setText("PREFERENZE NON DEL PARTITO SCELTO");
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    public void blankAction (ActionEvent e) {
        SchedaCategoricaPrefDao dao = new SchedaCategoricaPrefDao();
        SchedaCategoricaPref sr;
        alertLabel.setText("");
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: SCHEDA BIANCA?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaCategoricaPref(0,null,null, null,1);
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
                cands[j] = cand[j].getCf();//cand[j].getNome() + " " + cand[j].getCognome() /*+ " "+ cand[j].getSesso*/;
        }
        pref1Box.getItems().addAll(cands);
        pref2Box.getItems().addAll(cands);

    }
}

