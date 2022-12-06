package com.bsuir.green.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccesfullRegistationController {
    @FXML
    private Button backButton;

    @FXML
    void onBackButton() throws IOException {
        ((Stage) backButton.getScene().getWindow()).close();
    }
}
