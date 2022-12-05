package com.bsuir.green.controller.Stuff;

import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccesfulMakeVerdictPopupController {
    @FXML
    Button backButton;
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "popUpWindows/succesful-make-verdict-popup.fxml", "Успешная запись!");
    }
    public void onBackButton() throws IOException {
        new StuffViewController().show((Stage) backButton.getScene().getWindow());

    }
}
