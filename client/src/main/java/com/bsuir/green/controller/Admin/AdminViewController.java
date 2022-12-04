package com.bsuir.green.controller.Admin;

import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.controller.UserLogInController;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewController {
    @FXML
    private Button stuffControlButton;
    @FXML
    private Button createReportButton;
    @FXML
    private Button exitFromAccButton;

    Stuff currentStuff = null;
    public void show(Stage stage, Stuff stuff) throws IOException {
        currentStuff = stuff;
        ViewUtils.loadView(stage, "admin-view.fxml", "Администрирование");
    }
    public void onExitButton() throws  IOException{
        new UserLogInController().show((Stage) exitFromAccButton.getScene().getWindow());
    }
    public void onCreateReportButton(){

    }
    public void onControlStuffButton() throws IOException{
        new AdminStuffTableController().show((Stage) stuffControlButton.getScene().getWindow(), currentStuff);
    }

}
