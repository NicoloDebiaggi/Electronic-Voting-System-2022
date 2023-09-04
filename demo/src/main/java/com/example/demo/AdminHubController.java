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

public class AdminHubController {
    private boolean checkElection;
    @FXML
    private Button manageButton;
    @FXML
    private Button newButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button newPartyButton;
    @FXML
    private Button newNomineeButton;
    @FXML
    private Button editPartyButton;
    @FXML
    private Button delNomineeButton;
    @FXML
    private Button delPartyButton;
    @FXML
    private Label alertLabel;

    public void delNomineeButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();
        if(el==null) {
            try {
                Stage stage = (Stage) delNomineeButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("delCandidato.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        } else {
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }

    public void delPartyButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();
        if(el==null) {
            try {
                Stage stage = (Stage) delPartyButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("delPartito.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        }else{
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }

    public void newPartyButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();
        if(el==null) {
            try {
                Stage stage = (Stage) newPartyButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("creaPartito.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        }else{
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }
    public void newNomineeButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        if(edao.retrieveElection()==null) {
            try {
                Stage stage = (Stage) newNomineeButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("inserisciCandidato.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        }else {
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }
    public void editPartyButtonOnAction(ActionEvent e){
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();
        if(el==null) {
            try {
                Stage stage = (Stage) editPartyButton.getScene().getWindow();
                stage.close();
                Parent newRoot = FXMLLoader.load(getClass().getResource("candidatoInPartito.fxml"));
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(newRoot, 520, 400));
                newStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        }else{
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }
    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void manageButtonOnAction(ActionEvent e){
        ElectionDao edao = new ElectionDao();
        Election el = edao.retrieveElection();
        if(el!=null) {
            try {
                Stage sstage = (Stage) manageButton.getScene().getWindow();
                sstage.close();
                Parent snewRoot = FXMLLoader.load(getClass().getResource("manageElezione.fxml"));
                Stage snewStage = new Stage();
                snewStage.initStyle(StageStyle.UNDECORATED);
                snewStage.setScene(new Scene(snewRoot, 520, 400));
                snewStage.show();
            } catch (Exception er) {
                er.printStackTrace();
                er.getCause();
            }
        } else {
            alertLabel.setText("NESSUNA ELEZIONE IN CORSO");
        }
    }

    public void newButtonOnAction(ActionEvent e) {
        ElectionDao edao = new ElectionDao();
        alertLabel.setText("");
        if(edao.retrieveElection()==null) {
            try {
                if (!checkElection) {
                    checkElection = true;
                    Stage stage = (Stage) newButton.getScene().getWindow();
                    stage.close();
                    Parent newRoot = FXMLLoader.load(getClass().getResource("NewScheda.fxml"));
                    Stage newStage = new Stage();
                    newStage.initStyle(StageStyle.UNDECORATED);
                    newStage.setScene(new Scene(newRoot, 520, 400));
                    newStage.show();
                } else {
                    manageButtonOnAction(e);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                ex.getCause();
            }
        }else{
            alertLabel.setText("ELEZIONE IN CORSO");
        }
    }
}