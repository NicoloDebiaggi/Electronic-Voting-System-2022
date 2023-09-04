package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class resCatController implements Initializable {
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel;
    @FXML
    private VBox vertVBox;
    @FXML
    private Label blankLabel;
    @FXML
    private Label totLabel;
    @FXML
    private Label winnerLabel;

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
        idLabel.setText(String.valueOf(ed.retrieveElection().getId()));
        String tv = ed.retrieveElection().getTipoVittoria();
        ed.closeElection();
        SchedaCategoricaDao dao = new SchedaCategoricaDao();
        SchedaCategorica[] sc = dao.retrieveSchede();
        CandidatoDao cdao = new CandidatoDao();
        Candidato[] cand = cdao.retrieveAll();
        int res=0;
        int blank=0;
        int[] votesC= new int[cand.length];
        if(sc!=null && cand.length!=0) {
            //candidati+voti
            for (int i = 0; i < cand.length; i++) {
                for (int j = 0; j < sc.length; j++) {
                    if (cand[i].getCf().equals(sc[j].getCandidato_partito())) {
                        votesC[i]++;
                        res++;
                    }
                    Label lb = new Label(cand[i].getCf()+ " voti:"+String.valueOf(votesC[i]));
                    lb.setLayoutX(0);
                    lb.setLayoutY(i * 100);
                    vertVBox.getChildren().addAll(lb);
                }
            }
            //schede bianche
            for (int j = 0; j < sc.length; j++) {
                if ((sc[j].getBlank()) == 1) {
                    blank++;
                    res++;
                }
            }
        }
        int cmax=0;
        int max =0;
        int imax=0;
        for(int s=0;s<votesC.length;s++) {
            if(votesC[s]>max) {
                max=votesC[s];
                imax=s;
            }
        }
        for(int s=0;s<votesC.length;s++) {
            if(max==votesC[s] && s!=imax) {
                cmax++;
            }
        }
        String s="";
        if (cand.length==0) {
            s="NON CI SONO CANDIDATI";
            winnerLabel.setText(s);
        } else {
            if (tv.equals("maggioranza")) {
                //cerco massimo in votes, salvo index e metto cand[index]
                s = cand[imax].getNome() + " " + cand[imax].getCognome();
            } else if (tv.equals("maggioranza assoluta")) {
                //cerco massimo in votes e controllo se ha più del %50+1 dei voti rispetto a res
                if (max >= ((res / 2) + 1)) {
                    s = cand[imax].getNome() + " " + cand[imax].getCognome();
                } else {
                    s = "NO MAGGIORANZA ASSOLUTA";
                }
            }
            if (blank < res && cmax == 0) {
                winnerLabel.setText(s);
            }else if(cmax>0) {
                winnerLabel.setText("PARITA'");
            }else {
                winnerLabel.setText("NESSUN VINCITORE");
            }
        }

        blankLabel.setText(String.valueOf(blank));
        totLabel.setText(String.valueOf(res));
        ed.wipeDatabase();
    }



}
