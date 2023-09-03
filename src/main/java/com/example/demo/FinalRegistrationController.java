package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FinalRegistrationController {
    @FXML
    private Button finalButton;

    public void finalButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) finalButton.getScene().getWindow();
        stage.close();
    }
}
