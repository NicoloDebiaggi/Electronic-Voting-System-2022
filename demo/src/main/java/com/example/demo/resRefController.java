package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class resRefController implements Initializable {
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel;
    @FXML
    private Label noLabel;
    @FXML
    private Label yesLabel;
    @FXML
    private Label blankLabel;
    @FXML
    private Label totLabel;
    @FXML
    private Label quorumLabel; //votanti = 47.160.244 (dati dal ministero dell'interno)

    int quorum = (47160244/2)+1;

    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void newButtonOnAction(ActionEvent e) {
        try {
                Stage stage = (Stage) newButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("adminHub.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ElectionDao ed = new ElectionDao();
        Election ele =  ed.retrieveElection();
        idLabel.setText(String.valueOf(ed.retrieveId(ele)));
        String tv = ed.retrieveElection().getTipoVittoria();
        ed.closeElection();
        yesLabel.setText("");
        noLabel.setText("");
        totLabel.setText("");
        blankLabel.setText("");
        quorumLabel.setText("");
        SchedaReferendumDao dao = new SchedaReferendumDao();
        SchedaReferendum[] sr = dao.retrieveSchede();
        int y = 0;
        int n = 0;
        int blank = 0;
        int res = 0;
        if(sr!=null) {
            for (int i = 0; i < sr.length; i++) {
                if (sr[i].getVoto().equals("si")) {
                    y++;
                } else if (sr[i].getVoto().equals("no")) {
                    n++;
                }
            }
            //schede bianche
            for (int j = 0; j < sr.length; j++) {
                if ((sr[j].getBlank()) == 1) {
                    blank++;
                    res++;
                }
            }
        }
        res = y + n + blank;
        yesLabel.setText(String.valueOf(y));
        noLabel.setText(String.valueOf(n));
        blankLabel.setText(String.valueOf(blank));
        totLabel.setText(String.valueOf(res));
        String s="";
        if (tv.equals("con quorum")) {
            if (res >= quorum)
                s="RAGGIUNTO";
            else
                s="NON RAGGIUNTO";
        } else {
            s="NON NECESSARIO";
        }
        quorumLabel.setText(s);
        ed.wipeDatabase();
    }
}
