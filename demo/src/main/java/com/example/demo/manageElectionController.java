package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class manageElectionController implements Initializable {
    @FXML
    private Button closeElectionButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label idLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label winLabel;

    public void exitButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("adminHub.fxml"));
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(new Scene(newRoot, 520, 400));
            newStage.show();
        } catch (Exception er) {
            er.printStackTrace();
            er.getCause();
        }
    }

    public void closeElectionButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();

        if(el!=null) {

            try {
                switch (el.getTipoVotazione()) {
                    case "voto categorico":
                        try{
                            Stage stage = (Stage) closeElectionButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("resCat.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;

                    case "voto categorico (partiti)":
                        try{
                                Stage astage = (Stage) closeElectionButton.getScene().getWindow();
                                astage.close();
                                Parent anewRoot = FXMLLoader.load(getClass().getResource("resCatPart.fxml"));
                                Stage anewStage = new Stage();
                                anewStage.initStyle(StageStyle.UNDECORATED);
                                anewStage.setScene(new Scene(anewRoot, 520, 400));
                                anewStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;

                    case "voto ordinale":
                        try{
                                Stage qstage = (Stage) closeElectionButton.getScene().getWindow();
                                qstage.close();
                                Parent qnewRoot = FXMLLoader.load(getClass().getResource("resOrd.fxml"));
                                Stage qnewStage = new Stage();
                                qnewStage.initStyle(StageStyle.UNDECORATED);
                                qnewStage.setScene(new Scene(qnewRoot, 520, 400));
                                qnewStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;

                    case "voto ordinale (partiti)":
                        try{
                                Stage bstage = (Stage) closeElectionButton.getScene().getWindow();
                                bstage.close();
                                Parent bnewRoot = FXMLLoader.load(getClass().getResource("resOrdPart.fxml"));
                                Stage bnewStage = new Stage();
                                bnewStage.initStyle(StageStyle.UNDECORATED);
                                bnewStage.setScene(new Scene(bnewRoot, 520, 400));
                                bnewStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;


                    case "voto categorico con pref.":
                        try{
                            Stage wstage = (Stage) closeElectionButton.getScene().getWindow();
                            wstage.close();
                            Parent wnewRoot = FXMLLoader.load(getClass().getResource("resCatPref.fxml"));
                            Stage wnewStage = new Stage();
                            wnewStage.initStyle(StageStyle.UNDECORATED);
                            wnewStage.setScene(new Scene(wnewRoot, 520, 400));
                            wnewStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;

                    case "referendum":
                        try {
                            Stage estage = (Stage) closeElectionButton.getScene().getWindow();
                            estage.close();
                            Parent enewRoot = FXMLLoader.load(getClass().getResource("resRef.fxml"));
                            Stage enewStage = new Stage();
                            enewStage.initStyle(StageStyle.UNDECORATED);
                            enewStage.setScene(new Scene(enewRoot, 520, 400));
                            enewStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                        break;
                }

            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        }
    }

    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle) {
        ElectionDao eldao = new ElectionDao();
        Election el = eldao.retrieveElection();
        if(el!=null) {
            idLabel.setText(String.valueOf(el.getId()));
            idLabel.setAlignment(Pos.BOTTOM_LEFT);
            dateLabel.setText(el.getData().substring(0,2) + "/" + el.getData().substring(3,5) + "/" + el.getData().substring(6, 10));
            dateLabel.setAlignment(Pos.BOTTOM_LEFT);
            typeLabel.setText(el.getTipoVotazione());
            typeLabel.setAlignment(Pos.BOTTOM_LEFT);
            winLabel.setText(el.getTipoVittoria());
            winLabel.setAlignment(Pos.BOTTOM_LEFT);
        }
    }
}
