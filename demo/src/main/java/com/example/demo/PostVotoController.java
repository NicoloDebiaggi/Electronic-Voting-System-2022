package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PostVotoController implements Initializable {
    @FXML
    private Button finalCancButton;
    public void finalCancButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) finalCancButton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
