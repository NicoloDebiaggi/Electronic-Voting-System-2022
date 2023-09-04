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
import java.util.ResourceBundle;

public class resCatPartController implements Initializable {
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
        Election ele =  ed.retrieveElection();
        idLabel.setText(String.valueOf(ed.retrieveId(ele)));
        String tv = ed.retrieveElection().getTipoVittoria();
        ed.closeElection();
        winnerLabel.setText("");
        SchedaCategoricaDao dao = new SchedaCategoricaDao();
        SchedaCategorica[] sc = dao.retrieveSchede();

        PartitoDao pdao = new PartitoDao();
        Partito[] par = pdao.retrieveAll();
        int res = 0;
        int blank = 0;
        //partiti+voti
        int[] votesP = new int[par.length];;
        if(sc!=null && par.length!=0) {
            for (int i = 0; i < par.length; i++) {
                for (int j = 0; j < sc.length; j++) {
                    if (par[i].getNomeId().equals(sc[j].getCandidato_partito())) {
                        votesP[i]++;
                        res++;
                    }
                    Label lb = new Label(par[i].getNomeId()+" voti:"+String.valueOf(votesP[i]));
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
        for(int s=0;s<votesP.length;s++) {
            if(votesP[s]>max) {
                max=votesP[s];
                imax=s;
            }
        }
        for(int s=0;s<votesP.length;s++) {
            if(max==votesP[s] && s!=imax) {
                cmax++;
            }
        }
        String s="";
        if(par.length==0) {
            s="NON CI SONO PARTITI";
            winnerLabel.setText(s);
        } else{
            if(tv.equals("maggioranza")) {
                //cerco massimo in votes, salvo index e metto cand[index]
                s=par[imax].getNomeId();
            } else if (tv.equals("maggioranza assoluta")) {
                //cerco massimo in votes e controllo se ha più del %50+1 dei voti rispetto a res
                if (max >= ((res / 2) + 1)) {
                    s = par[imax].getNomeId();
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
