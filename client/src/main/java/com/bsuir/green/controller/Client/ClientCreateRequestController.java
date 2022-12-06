package com.bsuir.green.controller.Client;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.SpecialistListCommand;
import com.bsuir.green.common.command.createCommands.CreateDetailCommand;
import com.bsuir.green.common.command.createCommands.CreateRequestCommand;
import com.bsuir.green.common.command.getCommands.GetDetailCommand;
import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.*;
import com.bsuir.green.enums.DetailType;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientCreateRequestController implements Initializable {
    @FXML
    TextField detailName;
    @FXML
    ComboBox<String> cbDetailType = new ComboBox();
    @FXML
    ComboBox cbChosenStuff = new ComboBox<>();
    @FXML
    Button backButton;
    @FXML
    Button createRequest;


    static com.bsuir.green.common.model.Client currentClient = null;
    static Detail currentDetail = null;
    static ArrayList<Stuff> stuffArrayList = new ArrayList<>();


    public void show(Stage stage, com.bsuir.green.common.model.Client client) throws IOException {
        currentClient = client;
        ViewUtils.loadView(stage, "clientViews/client-make-request.fxml", "Создание запроса");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDetailType.getItems().addAll(DetailType.getLables());//закидываем значения в комбобокс с типами деталей
        //region Инициализируем comboBox с сотрудниками

        SpecialistListCommand stuffListCommand = new SpecialistListCommand();
        Client.writeObject(stuffListCommand);
        Object response = Client.readObject();
        if (response instanceof SpecialistListResponse) {
            stuffArrayList = ((SpecialistListResponse) response).getStuff();
        }
        cbChosenStuff.getItems().addAll(stuffArrayList);
        //endregion
    }

    public void onCreateRequestButton() throws IOException {
        createDetail();
        Detail detail = getDetail(new Detail(cbDetailType.getValue(), detailName.getText()));
        Stuff chosenStuff = (Stuff)cbChosenStuff.getValue();
        CreateRequestCommand createRequestCommand = new CreateRequestCommand(
                new Request(currentClient.getId(), detail.getId(), chosenStuff.getId()));
        Client.writeObject(createRequestCommand);
        Object response = Client.readObject();
        if (response instanceof CreateRequestResponse) {
            //todo поменять на Popup окошко
            onBackButton();
        }

    }

    public void onBackButton() throws IOException {
        new ClientViewController().show((Stage) backButton.getScene().getWindow(), currentClient);
    }

    public void createDetail() {
        CreateDetailCommand createDetailCommand =
                new CreateDetailCommand(new Detail(cbDetailType.getValue(), detailName.getText()));
        Client.writeObject(createDetailCommand);
        Object response = Client.readObject();
        if (response instanceof CreateDetailResponse) {
        }
    }

    public Detail getDetail(Detail detail) {
        GetDetailCommand getDetailCommand = new GetDetailCommand(detail);
        Client.writeObject(getDetailCommand);
        Object response = Client.readObject();
        if (response instanceof GetDetailResponse) {
            return new Detail(((GetDetailResponse) response).getDetail());
        }
        return null;
    }
}
