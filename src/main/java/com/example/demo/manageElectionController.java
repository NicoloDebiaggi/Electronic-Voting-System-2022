package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class manageElectionController {
    @FXML
    private Button closeElectionButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel; //=elezione.id
    @FXML
    private Label dateLabel; //= elezione.date
    @FXML
    private Label typeLabel;/*

    switch(elezione.tipo) {
         case 'C': typeLabel = "Voto categorico"; break;
         case 'O': typeLabel = "Voto ordinale"; break;
         case 'P': typeLabel = "Voto categorico con preferenza"; break;
         case 'R': typeLabel = "Referendum"; break;
    }*/
    @FXML
    private Label winLabel; //=elezione.modWin


    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void closeElectionButtonOnAction(ActionEvent e) {
        try {/*
                switch(elezione.tipo) {
                    case 'C'//voto categorico:  elezione.status=false;
                                                elezione.res=resCat(elezione.id);
                                                Stage stage = (Stage) manageButton.getScene().getWindow();
                                                stage.close();
                                                Parent newRoot = FXMLLoader.load(getClass().getResource("resCat.fxml"));
                                                Stage newStage = new Stage();
                                                newStage.initStyle(StageStyle.UNDECORATED);
                                                newStage.setScene(new Scene(newRoot, 520, 400));
                                                newStage.show();
                                                break;

                    case 'O'//voto ordinale:    elezione.status=false;
                                                elezione.res=resOrd(elezione.id);
                                                Stage stage = (Stage) manageButton.getScene().getWindow();
                                                stage.close();
                                                Parent newRoot = FXMLLoader.load(getClass().getResource("resOrd.fxml"));
                                                Stage newStage = new Stage();
                                                newStage.initStyle(StageStyle.UNDECORATED);
                                                newStage.setScene(new Scene(newRoot, 520, 400));
                                                newStage.show();
                                                break;

                    case 'P'//voto categorico con preferenza:   elezione.status=false;
                                                                elezione.res=resCatPref(elezione.id);
                                                                Stage stage = (Stage) manageButton.getScene().getWindow();
                                                                stage.close();
                                                                Parent newRoot = FXMLLoader.load(getClass().getResource("resCatPref.fxml"));
                                                                Stage newStage = new Stage();
                                                                newStage.initStyle(StageStyle.UNDECORATED);
                                                                newStage.setScene(new Scene(newRoot, 520, 400));
                                                                newStage.show();
                                                                break;

                    case 'R' //voto tramite referendum: elezione.status=false; elezione.res=resRef(elezione.id);
                                                        Stage stage = (Stage) manageButton.getScene().getWindow();
                                                        stage.close();
                                                        Parent newRoot = FXMLLoader.load(getClass().getResource("resRef.fxml"));
                                                        Stage newStage = new Stage();
                                                        newStage.initStyle(StageStyle.UNDECORATED);
                                                        newStage.setScene(new Scene(newRoot, 520, 400));
                                                        newStage.show();
                                                        break;
                }
             */

        }catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

}
