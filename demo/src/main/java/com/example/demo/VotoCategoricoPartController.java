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

public class VotoCategoricoPartController implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private ChoiceBox parChoiceBox;





    public void yesAction (ActionEvent e) {
        SchedaReferendumDao dao = new SchedaReferendumDao();
        SchedaReferendum sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: "+ parChoiceBox.getValue().toString().toUpperCase() +"?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaReferendum("si", 0);
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
        SchedaReferendumDao dao = new SchedaReferendumDao();
        SchedaReferendum sr;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: SCHEDA BIANCA?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                sr = new SchedaReferendum(null, 1);
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
            pars[i] = par[i].getDisplayName();
        }
        parChoiceBox.getItems().addAll(pars);
    }
}
