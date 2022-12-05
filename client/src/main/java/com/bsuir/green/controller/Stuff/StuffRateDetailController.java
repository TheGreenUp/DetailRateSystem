package com.bsuir.green.controller.Stuff;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.GetClientIdFromRequestCommand;
import com.bsuir.green.common.command.GetRateQuestionsCommand;
import com.bsuir.green.common.command.MakeResolutionCommand;
import com.bsuir.green.common.command.UpdateRequestStatusCommand;
import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.model.Resolution;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.*;
import com.bsuir.green.utils.ViewUtils;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StuffRateDetailController implements Initializable {
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
    static Stuff currentStuff = null;//ну тут она как бы не нужна
    static int requestId = -1;
    static Detail currentDetail = null;

    @FXML
    void onBackButton() throws IOException {
        new StuffViewController().show((Stage) backButton.getScene().getWindow(), currentStuff);
    }
    @FXML
    void onMakeVerdictButton() throws  IOException{

        GetClientIdFromRequestCommand gcifrc = new GetClientIdFromRequestCommand(requestId);
        Client.writeObject(gcifrc);
        Object response = Client.readObject();
        if (response instanceof GetClientIdFromRequestResponse) {
            response = makeResolution(response);
            if (response instanceof MakeResolutionResponse) {
                response = changeRequestStatus(requestId);
                if (response instanceof UpdateRequestStatusResponse) {
                    new SuccesfulMakeVerdictPopupController().show((Stage) makeVerdictButton.getScene().getWindow());
                }
            }
            else if (response instanceof ErrorResponse) {

            }
            else {

            }
        }
    }
    public void show(Stage stage, Stuff stuff, Detail detail, int currentRequestId) throws IOException {
        currentStuff = stuff;
        currentDetail = detail;
        requestId = currentRequestId;
        ViewUtils.loadView(stage, "stuffViews/stuff-rate-detail-view.fxml", "Оценка детали");
    }
    public Object makeResolution(Object resp){

        com.bsuir.green.common.model.Client client = ((GetClientIdFromRequestResponse) resp).getClient();
        MakeResolutionCommand makeResolutionCommand =
                new MakeResolutionCommand(
                        new Resolution(requestId,currentDetail.getId(), client.getId(),getRateResult()));
        Client.writeObject(makeResolutionCommand);
        return Client.readObject();
    }
    public Object changeRequestStatus(int requestId) {
        UpdateRequestStatusCommand updateRequestStatusCommand = new UpdateRequestStatusCommand(requestId);
        Client.writeObject(updateRequestStatusCommand);
        return Client.readObject();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //region Sliders
        answer_1.valueProperty().addListener((ObservableValue<? extends  Number> num, Number oldVal, Number newVal)->{});
        answer_2.valueProperty().addListener((ObservableValue<? extends  Number> num, Number oldVal, Number newVal)->{});
        answer_3.valueProperty().addListener((ObservableValue<? extends  Number> num, Number oldVal, Number newVal)->{});
        //endregion

        GetRateQuestionsCommand getQuestions = new GetRateQuestionsCommand(currentDetail);
        Client.writeObject(getQuestions);
        Object response = Client.readObject();
        if (response instanceof GetRateQuestionsRespose) {
            List<String> questions = ((GetRateQuestionsRespose) response).getQuestions();
            question_1.setText(questions.get(0));
            question_2.setText(questions.get(1));
            question_3.setText(questions.get(2));
        }
        else if (response instanceof ErrorResponse) {

        }
        else {
            //todo log.info;
        }
    }
    public String getRateResult(){
        int averageRate = (int) (answer_1.getValue() + answer_2.getValue() + answer_3.getValue());
        if (averageRate >= 20) {
            return "Сертифицирована";
        }
        return "Не подлежит сертификации";
    }
}
