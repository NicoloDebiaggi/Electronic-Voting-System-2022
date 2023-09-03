package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PostVotoController {
    @FXML
    private Button finalCancButton;

    public void finalCancButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) finalCancButton.getScene().getWindow();
        stage.close();
    }
}
