package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class VotoCategoricoController implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private ChoiceBox candChoiceBox;





    public void yesAction (ActionEvent e) {
        SchedaCategoricaDao dao = new SchedaCategoricaDao();
        SchedaCategorica sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: "+ candChoiceBox.getValue().toString().toUpperCase() +"?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaCategorica(candChoiceBox.getValue().toString().toUpperCase(), 0);
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
            }
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    public void blankAction (ActionEvent e) {
        SchedaCategoricaDao da0 = new SchedaCategoricaDao();
        SchedaCategorica sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: SCHEDA BIANCA?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaCategorica(null, 1);
                da0.createScheda(sr);
                BaseUserDao dao = new BaseUserDao();
                dao.setVoted(BaseUser.getInstance().getUsername());
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
        CandidatoDao dao = new CandidatoDao();
        Candidato[] cand= dao.retrieveAll();
        String[] nominees = new String[cand.length];
        for(int i=0;i<cand.length;i++) {
            nominees[i] = cand[i].getNome() + " " + cand[i].getCognome();
        }
        candChoiceBox.getItems().addAll(nominees);
    }
}
