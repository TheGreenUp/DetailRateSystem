package com.bsuir.green.controller.Stuff;

import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class StuffRateDetailController {
    //region Sliders
    @FXML
    private Slider answer_1;

    @FXML
    private Slider answer_2;

    @FXML
    private Slider answer_3;
    //endregion

    //region Buttons

    @FXML
    private Button backButton;
    @FXML
    private Button makeVerdictButton;
    //endregion

    //region Labels
    @FXML
    private Label question_1;

    @FXML
    private Label question_2;

    @FXML
    private Label question_3;
    //endregion
    static Stuff currentStuff = null;


    @FXML
    void onBackButton() throws IOException {
        new StuffViewController().show((Stage) backButton.getScene().getWindow(), currentStuff);
    }


    @FXML
    void onMakeVerdictButton() throws  IOException{
        //sout
    }

    public void show(Stage stage, Stuff stuff) throws IOException {
        currentStuff = stuff;
        ViewUtils.loadView(stage, "stuff-rate-detail-view.fxml", "Оценка детали");
    }

}
