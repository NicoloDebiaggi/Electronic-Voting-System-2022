package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class VotoOrdinaleController implements Initializable {
    @FXML
    private Button yesButton;
    @FXML
    private Button blankButton;
    @FXML
    private VBox candVBox;

    public void yesAction (ActionEvent e) {
        SchedaOrdinaleDao dao = new SchedaOrdinaleDao();
        SchedaOrdinale so;
        CandidatoDao cdao = new CandidatoDao();
        Candidato[] cand = cdao.retrieveAll();
        ObservableList<Node> ol;
        String[] res = new String[cand.length];
        try {
            ol = candVBox.getChildren();
            ComboBox cb1;
            for(int i=0; i<cand.length;i++) {
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
        CandidatoDao dao = new CandidatoDao();
        Candidato[] cand = dao.retrieveAll();
        for(int i=0;i<cand.length;i++) {
            ComboBox<String> btn = new ComboBox<String>();
            for(int j=0;j<cand.length;j++) {
                btn.getItems().add(cand[j].getNome() + " " + cand[j].getCognome());
            }
            btn.setLayoutX(i*200);
            btn.setLayoutY(0);
            candVBox.getChildren().add(btn);
            candVBox.fillWidthProperty();
        }
    }
}
