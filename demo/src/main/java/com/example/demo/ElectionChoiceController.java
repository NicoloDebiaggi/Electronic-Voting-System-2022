package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ElectionChoiceController {
    @FXML
    private Button exitButton;
    @FXML
    private Button goButton;
    @FXML
    private Label alertLabel;
    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void goButtonOnAction(ActionEvent e) {
        ElectionDao dao = new ElectionDao();
        alertLabel.setText("");
        if(dao.retrieveElection()==null) {
            alertLabel.setText("NESSUNA VOTAZIONE IN CORSO");
            alertLabel.setAlignment(Pos.CENTER);
        }else if ((BaseUser.getInstance().getVoted()==1)) {
            alertLabel.setText("QUESTO UTENTE HA GIA' VOTATO");
        } else {
            CandidatoDao cdao = new CandidatoDao();
            PartitoDao pdao = new PartitoDao();
            alertLabel.setText("");
            switch (dao.retrieveElection().getTipoVotazione()) {

                case "referendum":
                    try {
                        Stage stage = (Stage) goButton.getScene().getWindow();
                        stage.close();
                        Parent newRoot = FXMLLoader.load(getClass().getResource("votoReferendum.fxml"));
                        Stage newStage = new Stage();
                        newStage.initStyle(StageStyle.UNDECORATED);
                        newStage.setScene(new Scene(newRoot, 520, 400));
                        newStage.show();
                    } catch (Exception er) {
                        er.printStackTrace();
                        er.getCause();
                    }
                    break;
                case "voto categorico":
                    if(cdao.retrieveAll().length==0) {
                        alertLabel.setText("NESSUNA OPZIONE DI VOTO");
                    } else {
                        try {
                            Stage stage = (Stage) goButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("votoCategorico.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                    }
                    break;
                case "voto categorico (partiti)":
                    if(pdao.retrieveAll().length==0) {
                        alertLabel.setText("NESSUNA OPZIONE DI VOTO");
                    } else {
                        try {
                            Stage stage = (Stage) goButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("votoCategoricoPart.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                    }
                    break;
                case "voto categorico con pref.":
                    if(cdao.retrieveAll().length==0 && pdao.retrieveAll().length==0) {
                        alertLabel.setText("NESSUNA OPZIONE DI VOTO");
                    } else {
                        if (dao.retrieveElection().getTwoPrefs() == 1) {
                            try {
                                Stage stage = (Stage) goButton.getScene().getWindow();
                                stage.close();
                                Parent newRoot = FXMLLoader.load(getClass().getResource("votoCategoricoConPreferenza.fxml"));
                                Stage newStage = new Stage();
                                newStage.initStyle(StageStyle.UNDECORATED);
                                newStage.setScene(new Scene(newRoot, 520, 400));
                                newStage.show();
                            } catch (Exception er) {
                                er.printStackTrace();
                                er.getCause();
                            }
                        } else {
                            try {
                                Stage stage = (Stage) goButton.getScene().getWindow();
                                stage.close();
                                Parent newRoot = FXMLLoader.load(getClass().getResource("votoCategoricoConPreferenzaSolo1.fxml"));
                                Stage newStage = new Stage();
                                newStage.initStyle(StageStyle.UNDECORATED);
                                newStage.setScene(new Scene(newRoot, 520, 400));
                                newStage.show();
                            } catch (Exception er) {
                                er.printStackTrace();
                                er.getCause();
                            }
                        }
                    }
                    break;
                case "voto ordinale":
                    if(cdao.retrieveAll().length==0) {
                        alertLabel.setText("NESSUNA OPZIONE DI VOTO");
                    } else {
                        try {
                            Stage stage = (Stage) goButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("votoOrdinale.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                    }
                    break;
                case "voto ordinale (partiti)":
                    if(pdao.retrieveAll().length==0) {
                        alertLabel.setText("NESSUNA OPZIONE DI VOTO");
                    } else {
                        try {
                            Stage stage = (Stage) goButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("votoOrdinalePart.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                    }
                    break;
            }
        }

    }
}
