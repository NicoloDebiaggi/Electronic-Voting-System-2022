package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class NewElectionController implements Initializable {
    @FXML
    private Button confirmButton;
    @FXML
    private Button backButton;
    @FXML
    private Label alertLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label refLabel;
    @FXML
    private TextField dataTextField;
    @FXML
    private ChoiceBox<String> votoChoiceBox;
    @FXML
    private ChoiceBox<String> winChoiceBox;
    @FXML
    private  TextField questTextField;
    @FXML
    private CheckBox twoPrefsCheckBox;
    private String[] voto = {"referendum",  "voto categorico",  "voto categorico (partiti)", "voto categorico con pref.", "voto ordinale",  "voto ordinale (partiti)"};
    private String[] win = {"maggioranza", "maggioranza assoluta", "con quorum", "senza quorum"};

    public void backButtonOnAction(ActionEvent e) {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
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
    public boolean checkQuest() {
        return (votoChoiceBox.getValue().equals("referendum"));
    }

    public boolean checkEl() {
        return (votoChoiceBox.getValue().equals("referendum") && (winChoiceBox.getValue().equals("con quorum")) || winChoiceBox.getValue().equals("senza quorum")) || (!(votoChoiceBox.getValue().equals("referendum")) && (winChoiceBox.getValue().equals("maggioranza")) || winChoiceBox.getValue().equals("maggioranza assoluta"));
    }

    public boolean checkData() {
        if(dataTextField.getText().length() == 10) {
            String gg = dataTextField.getText().substring(0, 2);
            int g = Integer.parseInt(gg);
            String mm = dataTextField.getText().substring(3, 5);
            int m = Integer.parseInt(mm);
            String yyyy = dataTextField.getText().substring(6, 10);
            int y = Integer.parseInt(yyyy);
            if (y < 2022 || y > 2022 || m < 1 || m > 12 || g < 1 || g > 32) {
                return false;
            }
            switch (m) {
                case 2:
                    if (y % 400 == 0) {
                        if (g > 30) {
                            return false;
                        }
                    } else {
                        if (g > 29) {
                            return false;
                        }
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (g > 30) {
                        return false;
                    }
            }
            return true;
        }else{
            dataLabel.setText("FORMATO DATA SCORRETTO");
            return false;
        }

    }

    public void confirmButtonOnAction(ActionEvent e) {
        alertLabel.setText("");
        String data="";
        ElectionDao dao = new ElectionDao();
        if(dao.retrieveElection() != null) {
            alertLabel.setText("Elezione già in corso");
        } else {
            if (checkEl()) {
                alertLabel.setText("");
                dataLabel.setText("");
                refLabel.setText("");
                if (checkData()) {
                    alertLabel.setText("");
                    dataLabel.setText("");
                    refLabel.setText("");
                    if(checkQuest()) {
                        if(questTextField.getText()==null) {
                            refLabel.setText("INSERIRE DOMANDA PER REFERENDUM");
                        } else {
                            data = dataTextField.getText().substring(0, 2) + "/" + dataTextField.getText().substring(3, 5) + "/" + dataTextField.getText().substring(6, 10);
                            if(twoPrefsCheckBox.isSelected()) {
                                dao.createElection(new Election(data, votoChoiceBox.getValue(), winChoiceBox.getValue(), 1, 0, questTextField.getText()));
                            } else {
                                dao.createElection(new Election(data, votoChoiceBox.getValue(), winChoiceBox.getValue(), 1, 1, questTextField.getText()));
                            } try {
                                Stage stage = (Stage) confirmButton.getScene().getWindow();
                                stage.close();
                                Parent newRoot = FXMLLoader.load(getClass().getResource("manageElezione.fxml"));
                                Stage newStage = new Stage();
                                newStage.initStyle(StageStyle.UNDECORATED);
                                newStage.setScene(new Scene(newRoot, 520, 400));
                                newStage.show();
                            } catch (Exception er) {
                                er.printStackTrace();
                                er.getCause();
                            }
                        }
                    } else {
                        if(twoPrefsCheckBox.isSelected()) {
                            dao.createElection(new Election(dataTextField.getText(), votoChoiceBox.getValue(), winChoiceBox.getValue(), 1, 1, null));
                        }else{
                            dao.createElection(new Election(dataTextField.getText(), votoChoiceBox.getValue(), winChoiceBox.getValue(), 1, 0, null));
                        }try {
                            Stage stage = (Stage) confirmButton.getScene().getWindow();
                            stage.close();
                            Parent newRoot = FXMLLoader.load(getClass().getResource("manageElezione.fxml"));
                            Stage newStage = new Stage();
                            newStage.initStyle(StageStyle.UNDECORATED);
                            newStage.setScene(new Scene(newRoot, 520, 400));
                            newStage.show();
                        } catch (Exception er) {
                            er.printStackTrace();
                            er.getCause();
                        }
                    }
                } else {
                    dataLabel.setText("formato data incorretto o data non valida");
                }
            } else {
                alertLabel.setText("modalità di vittoria inesatta rispetto alla modalità di voto");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        votoChoiceBox.getItems().addAll(voto);
        winChoiceBox.getItems().addAll(win);
    }
}






