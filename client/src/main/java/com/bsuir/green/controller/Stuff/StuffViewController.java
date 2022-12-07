package com.bsuir.green.controller.Stuff;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.listCommands.RequestListForStuffCommand;
import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.model.RequestExtended;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.listRepsonse.RequestListForStuffResponse;
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
    private TableView<RequestExtended> infoTable;
    @FXML
    private TableColumn<RequestExtended, String> columnClientFName;
    @FXML
    private TableColumn<RequestExtended, String> columnClientLName;
    @FXML
    private TableColumn<RequestExtended, String> columnDetailName;

    @FXML
    private TableColumn<RequestExtended, String> columnDetailType;

    @FXML
    private TableColumn<RequestExtended, Integer> columnRequestId;

    @FXML
    private TableColumn<RequestExtended, String> columnRequestStatus;
    @FXML
    private TableColumn<RequestExtended, Integer> columnClientId;
    @FXML
    private TableColumn<RequestExtended, Integer> columnDetailId;
    //endregion

    //region Buttons
    @FXML
    private Button exitFromAccButton;
    @FXML
    private Button checkRequestButton;
    @FXML
    private Button updateTableBtn;
    //endregion

    //region Variables
    ObservableList<RequestExtended> requestList;
    static Stuff currentStuff = null;
    int chosenRequestIndex = -1;
    static RequestExtended chosenRequest = null;
    static Detail chosenDetail = null;
    //endregion
    public void show(Stage stage, Stuff stuff) throws IOException {
        currentStuff = stuff;
        ViewUtils.loadView(stage, "stuffViews/stuff-view.fxml", "Работка");
    }
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuffViews/stuff-view.fxml", "Работка");
    }
    public void onExitButton() throws IOException {
        new UserLogInController().show((Stage) exitFromAccButton.getScene().getWindow());
    }

    public void onCheckRequestButton()throws IOException {
         new StuffRateDetailController().show((Stage) checkRequestButton.getScene().getWindow(), currentStuff, chosenDetail, chosenRequest.getId());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequestListForStuffCommand requestListForStuffCommand = new RequestListForStuffCommand(currentStuff);
        Client.writeObject(requestListForStuffCommand);
        Object response = Client.readObject();
        if (response instanceof RequestListForStuffResponse) {
            requestList = FXCollections.observableList((List<RequestExtended>) ((RequestListForStuffResponse) response).getRequests());
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
        chosenRequest = new RequestExtended(
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
    }
    private void setCellTable(ObservableList<RequestExtended> requestList) {
        columnDetailName.setCellValueFactory(new PropertyValueFactory<RequestExtended, String>("detailName"));
        columnDetailType.setCellValueFactory(new PropertyValueFactory<RequestExtended, String>("detailType"));
        columnClientFName.setCellValueFactory(new PropertyValueFactory<RequestExtended, String>("clientFname"));
        columnClientLName.setCellValueFactory(new PropertyValueFactory<RequestExtended, String>("clientLname"));
        columnRequestStatus.setCellValueFactory(new PropertyValueFactory<RequestExtended, String>("requestStatus"));
        columnRequestId.setCellValueFactory(new PropertyValueFactory<RequestExtended, Integer>("id"));
        columnClientId.setCellValueFactory(new PropertyValueFactory<RequestExtended, Integer>("client_id"));
        columnDetailId.setCellValueFactory(new PropertyValueFactory<RequestExtended, Integer>("detail_id"));
        infoTable.setItems(requestList);

    }

}
