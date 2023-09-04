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

public class resCatPrefController implements Initializable {
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel;
    @FXML
    private VBox vertVBox;
    @FXML
    private VBox prefVBox;
    @FXML
    private Label blankLabel;
    @FXML
    private Label totLabel;
    @FXML
    private Label winnerLabel;
    @FXML
    private Label prefLabel;

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

        SchedaCategoricaPrefDao dao = new SchedaCategoricaPrefDao();
        SchedaCategoricaPref[] sc = dao.retrieveSchede();

        CandidatoDao cdao = new CandidatoDao();
        Candidato[] cand = cdao.retrieveAll();
        PartitoDao pdao = new PartitoDao();
        Partito[] par = pdao.retrieveAll();

        int res = 0;
        int blank = 0;
        System.out.println(sc.length);
        int[] votesPP = new int[cand.length];;
        int[] votesP = new int[par.length];;
        if(sc.length!=0 && par.length!=0) {
            //partiti+voti
            res=sc.length;
            for (int i = 0; i < par.length; i++) {
                System.out.println("-"+par[i].getNomeId()+sc[i].getPartito());
                for (int j = 0; j < sc.length; j++) {
                    if (par[i].getNomeId().equals(sc[j].getPartito())) {
                        System.out.println(sc[i].getPartito()+ "");
                        votesP[i]++;
                    }
                }
                Label lb = new Label(par[i].getDisplayName()+" voti:"+String.valueOf(votesP[i]));
                lb.setLayoutX(0);
                lb.setLayoutY(i * 100);
                vertVBox.getChildren().addAll(lb);
            }
            if(sc!=null && cand.length!=0) {
                //preferenze+voti
                for (int c = 0; c < cand.length; c++) {
                    for (int p = 0; p < sc.length; p++) {
                        if ((cand[c].getCf().equals(sc[p].getCandidato1())) || (cand[c].getCf().equals(sc[p].getCandidato2()))) {
                            votesPP[c]++;
                        }
                    }
                    Label lc = new Label(cand[c].getCf() + " voti:"+String.valueOf(votesPP[c]) +" partito di appartenenza:" +cdao.retrievePartitoId(cand[c].getCf()));
                    lc.setLayoutX(0);
                    lc.setLayoutY(c * 200);
                    prefVBox.getChildren().addAll(lc);
                }
            }
            //schede bianche
            for (int j = 0; j < sc.length; j++) {
                if ((sc[j].getBlank() == 1)) {
                    blank++;
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
        } else {
            if (tv.equals("maggioranza")) {
                //cerco massimo in votes, salvo index e metto cand[index]
                s = par[imax].getNomeId();
            } else if (tv.equals("maggioranza assoluta")) {
                //cerco massimo in votes e controllo se ha più del %50+1 dei voti rispetto a res
                if (max >= ((res / 2) + 1)) {
                    s = par[imax].getNomeId();
                } else {
                    s = "maggioranza assoluta non raggiunta";
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
        int pmax=0;
        int ipmax=0;
        int icmax=0;
        String s1="";
        for(int s2=0;s2<votesPP.length;s2++) {
            if((votesPP[s2]>pmax) && (cdao.retrievePartitoId(cand[s2].getCf()).equals(par[imax].getNomeId()))) {
                pmax=votesPP[s2];
                ipmax=s2;
            }
        }
        for(int s2=0;s2<votesP.length;s2++) {
            if(max==votesP[s2] && s2!=ipmax && (cdao.retrievePartitoId(cand[s2].getCf()).equals(par[imax].getNomeId()))) {
                icmax++;
            }
        }
        if(cand.length==0) {
            s1="NON CI SONO CANDIDATI";
            prefLabel.setText(s1);
        } else {
            if (tv.equals("maggioranza")) {
                //cerco massimo in votes, salvo index e metto cand[index]
                s1 = cand[ipmax].getNome() + cand[ipmax].getCognome();
                prefLabel.setText(s1);
            } else if (tv.equals("maggioranza assoluta")) {
                //cerco massimo in votes e controllo se ha più del %50+1 dei voti rispetto a res
                if (max >= ((res / 2) + 1)) {
                    s1 = cand[ipmax].getNome() + cand[ipmax].getCognome();
                } else {
                    s1 = "maggioranza assoluta non raggiunta";
                }
                prefLabel.setText(s1);
            }
            if (blank < res && icmax == 0) {
                prefLabel.setText(s1);
            }else if(icmax>0) {
                prefLabel.setText("PARITA'");
            }else {
                prefLabel.setText("NESSUN VINCITORE");
            }
        }
        blankLabel.setText(String.valueOf(blank));
        totLabel.setText(String.valueOf(res));
        ed.wipeDatabase();
    }
}
