package com.bsuir.green.controller;

import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientViewController {

    @FXML
    private Button makeRequest;
    @FXML
    private Button showResolution;
    @FXML
    private Button exitFromAcc;
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "client-view.fxml", "Главное меню");
    }
    public void onExitButton() throws IOException{
        new LogInController().show((Stage) exitFromAcc.getScene().getWindow());
    }
    public void onShowResolutionButton(){

    }
    public void onMakeRequestButton(){

    }
}