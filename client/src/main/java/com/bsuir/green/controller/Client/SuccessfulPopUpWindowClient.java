package com.bsuir.green.controller.Client;

import com.bsuir.green.common.model.Client;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccessfulPopUpWindowClient {
    @FXML
    Button backButton;
    static Client currentClient = null;
    public void show(Stage stage,Client client) throws IOException {
        currentClient = client;
        ViewUtils.loadView(stage, "popUpWindows/succesful-make-verdict-popup.fxml", "Успешная запись!");
    }
    public void onBackButton() throws IOException {
        new ClientViewController().show((Stage) backButton.getScene().getWindow(), currentClient);

    }
}
