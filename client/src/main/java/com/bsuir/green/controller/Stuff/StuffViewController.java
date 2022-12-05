package com.bsuir.green.controller.Stuff;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.RequestListForStuffCommand;
import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.model.RequestForStuff;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.RequestListForStuffResponse;
import com.bsuir.green.controller.UserLogInController;
import com.bsuir.green.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StuffViewController implements Initializable {

    //region Table
    @FXML
    private TableView<RequestForStuff> infoTable;
    @FXML
    private TableColumn<RequestForStuff, String> columnClientFName;
    @FXML
    private TableColumn<RequestForStuff, String> columnClientLName;
    @FXML
    private TableColumn<RequestForStuff, String> columnDetailName;

    @FXML
    private TableColumn<RequestForStuff, String> columnDetailType;

    @FXML
    private TableColumn<RequestForStuff, Integer> columnRequestId;

    @FXML
    private TableColumn<RequestForStuff, String> columnRequestStatus;
    @FXML
    private TableColumn<RequestForStuff, Integer> columnClientId;
    @FXML
    private TableColumn<RequestForStuff, Integer> columnDetailId;
    //endregion

    //region Buttons
    @FXML
    private Button exitFromAccButton;
    @FXML
    private Button checkRequestButton;
    //endregion

    //region Variables
    ObservableList<RequestForStuff> requestList;
    static Stuff currentStuff = null;
    int chosenRequestIndex = -1;
    static int requestId = -1;
    static RequestForStuff chosenRequest = null;
    static Detail chosenDetail = null;
    //endregion
    public void show(Stage stage, Stuff stuff) throws IOException {
        currentStuff = stuff;
        ViewUtils.loadView(stage, "stuff-view.fxml", "Работка");
    }

    public void onExitButton() throws IOException {
        new UserLogInController().show((Stage) exitFromAccButton.getScene().getWindow());
    }

    public void onCheckRequestButton()throws IOException {
        new StuffRateDetailController().show((Stage) checkRequestButton.getScene().getWindow(), currentStuff, chosenDetail, requestId);

    }

    private void refreshTable() {//todo тут одинаковый код с init, но я хз как поправить, ибо при удалении и добавлении хотелось бы, чтобы сразу таблица обновлялась
        RequestListForStuffCommand requestListForStuffCommand = new RequestListForStuffCommand(currentStuff);
        Client.writeObject(requestListForStuffCommand);
        Object response = Client.readObject();
        if (response instanceof RequestListForStuffResponse) {
            requestList = FXCollections.observableList((List<RequestForStuff>) ((RequestListForStuffResponse) response).getRequests());
            setCellTable(requestList);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequestListForStuffCommand requestListForStuffCommand = new RequestListForStuffCommand(currentStuff);
        Client.writeObject(requestListForStuffCommand);
        Object response = Client.readObject();
        if (response instanceof RequestListForStuffResponse) {
            requestList = FXCollections.observableList((List<RequestForStuff>) ((RequestListForStuffResponse) response).getRequests());
            setCellTable(requestList);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }

    @FXML
    void getSelected(MouseEvent mouseEvent) {
        chosenRequestIndex = infoTable.getSelectionModel().getSelectedIndex();
        if (chosenRequestIndex <= -1) {//проверка на то, выделил ли что-нибудь пользователь или нет
            return;
        }
        //region Создание реквеста
        chosenRequest = new RequestForStuff(
                columnRequestId.getCellData(chosenRequestIndex),
                columnClientFName.getCellData(chosenRequestIndex),
                columnClientLName.getCellData(chosenRequestIndex),
                columnDetailName.getCellData(chosenRequestIndex),
                columnDetailType.getCellData(chosenRequestIndex),
                columnRequestStatus.getCellData(chosenRequestIndex));
        //endregion

        //region Создание детальки
        chosenDetail = new Detail(
                columnDetailType.getCellData(chosenRequestIndex),
                columnDetailName.getCellData(chosenRequestIndex),
                columnDetailId.getCellData(chosenRequestIndex));
        //endregion

         requestId = columnRequestId.getCellData(chosenRequestIndex);
    }
    private void setCellTable(ObservableList<RequestForStuff> requestList) {
        columnDetailName.setCellValueFactory(new PropertyValueFactory<RequestForStuff, String>("detailName"));
        columnDetailType.setCellValueFactory(new PropertyValueFactory<RequestForStuff, String>("detailType"));
        columnClientFName.setCellValueFactory(new PropertyValueFactory<RequestForStuff, String>("clientFname"));
        columnClientLName.setCellValueFactory(new PropertyValueFactory<RequestForStuff, String>("clientLname"));
        columnRequestStatus.setCellValueFactory(new PropertyValueFactory<RequestForStuff, String>("requestStatus"));
        columnRequestId.setCellValueFactory(new PropertyValueFactory<RequestForStuff, Integer>("id"));
        columnClientId.setCellValueFactory(new PropertyValueFactory<RequestForStuff, Integer>("client_id"));
        columnDetailId.setCellValueFactory(new PropertyValueFactory<RequestForStuff, Integer>("detail_id"));
        infoTable.setItems(requestList);

    }

}
