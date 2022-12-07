package com.bsuir.green.controller.Client;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.getCommands.GetDetailedResolutionCommand;
import com.bsuir.green.common.model.DetailedResolution;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.getResponse.GetDetailedResolutionResponse;
import com.bsuir.green.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ClientDetailViewController implements Initializable {
    @FXML
    private Button backButton;

    //region Table
    @FXML
    private TableColumn<DetailedResolution, String> columnDetail;

    @FXML
    private TableColumn<DetailedResolution, String> columnDetailName;

    @FXML
    private TableColumn<DetailedResolution, String> columnDetailType;

    @FXML
    private TableColumn<DetailedResolution, String> columnRequestStatus;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuff;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuffName;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuffSurname;
    @FXML
    private TableColumn<DetailedResolution, Date> columnDate;

    @FXML
    private TableView<DetailedResolution> infoTable;
    //endregion

    ObservableList<DetailedResolution> resolutionList;
    static com.bsuir.green.common.model.Client client = null;

    @FXML
    void onBackButton() throws IOException {
        new ClientViewController().show((Stage) backButton.getScene().getWindow(), client);
    }
    @FXML
    public void show(Stage stage, com.bsuir.green.common.model.Client client) throws IOException {
        this.client = client;
        ViewUtils.loadView(stage, "clientViews/client-details-view.fxml", "Просмотр заказов");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo Отображаются не свои заказы
        GetDetailedResolutionCommand getDetailedResolutionCommand = new GetDetailedResolutionCommand();
        Client.writeObject(getDetailedResolutionCommand);
        Object response = Client.readObject();
        if (response instanceof GetDetailedResolutionResponse) {
            resolutionList = FXCollections.observableList(((GetDetailedResolutionResponse) response).getResolutions());
            setCellTable(resolutionList);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }
    private void setCellTable(ObservableList<DetailedResolution> resolutions) {
        columnDetailType.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("detailType"));
        columnDetailName.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("detailName"));
        columnStuffSurname.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("stuffLname"));
        columnStuffName.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("stuffFname"));
        columnRequestStatus.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("result"));
        columnDate.setCellValueFactory(new PropertyValueFactory<DetailedResolution, Date>("date"));
        infoTable.setItems(resolutions);
    }

}
