package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class InserisciCandidatoController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Label alertLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField cfTextField;
    @FXML
    private Label confLabel;
    @FXML
    private ChoiceBox sexChoiceBox;
    String[] sex = {"M", "F"};
    public void addButtonOnAction(ActionEvent e) {
        CandidatoDao dao = new CandidatoDao();
        confLabel.setText("");
        alertLabel.setText("");
        if(dao.retrieveCandidato(cfTextField.getText()) == null) {
            dao.createCandidato(new Candidato(nameTextField.getText(), lastNameTextField.getText(), cfTextField.getText(), sexChoiceBox.getValue().toString()));
            alertLabel.setText("");
            confLabel.setText("CANDIDATO INSERITO");

        } else {
            confLabel.setText("");
            alertLabel.setText("CANDIDATO GIA' ESISTENTE");
        }
        alertLabel.setText("");
        confLabel.setText("PROSSIMA INSERZIONE");
    }
    public void backButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        try {
            Stage estage = (Stage) backButton.getScene().getWindow();
            estage.close();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexChoiceBox.getItems().addAll(sex);
    }
}
