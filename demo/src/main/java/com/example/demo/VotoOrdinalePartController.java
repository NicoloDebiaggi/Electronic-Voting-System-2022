package com.example.demo;

import com.example.demo.Candidato;
import com.example.demo.CandidatoDao;
import com.example.demo.SchedaOrdinale;
import com.example.demo.SchedaOrdinaleDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class VotoOrdinalePartController implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private VBox candVBox;

    public void yesAction (ActionEvent e) {
        SchedaOrdinaleDao dao = new SchedaOrdinaleDao();
        SchedaOrdinale so;
        PartitoDao pdao = new PartitoDao();
        Partito[] par = pdao.retrieveAll();
        ObservableList<Node> ol;
        String[] res = new String[par.length];
        try {
            ol = candVBox.getChildren();
            ComboBox cb1;
            for(int i=0; i<par.length;i++) {
                cb1 = (ComboBox) (ol.get(i));
                res[i] = cb1.getValue().toString();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare l'ordine dei propri voti?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                so = new SchedaOrdinale(res, 0);
                dao.createScheda(so);
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
        SchedaOrdinaleDao dao = new SchedaOrdinaleDao();
        SchedaOrdinale so;
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confermare il proprio voto: SCHEDA BIANCA?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                so = new SchedaOrdinale(null, 1);
                dao.createScheda(so);
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

        for(int i=0;i<par.length;i++) {
            ComboBox<String> btn = new ComboBox<String>();
            for(int j=0;j<par.length;j++) {
                btn.getItems().add(par[j].getDisplayName());
            }
            btn.setLayoutX(i*200);
            btn.setLayoutY(0);
            candVBox.getChildren().add(btn);
            candVBox.fillWidthProperty();
        }
    }
}
