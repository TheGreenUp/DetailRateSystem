package com.bsuir.green.controller.Admin;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.getCommands.GetDetailedResolutionCommand;
import com.bsuir.green.common.model.DetailedResolution;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.GetDetailedResolutionResponse;
import com.bsuir.green.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminReportTableController implements Initializable {

    //region Table
    @FXML
    private TableColumn<DetailedResolution, String> columnClient;

    @FXML
    private TableColumn<DetailedResolution, String> columnClientEmail;

    @FXML
    private TableColumn<DetailedResolution, String> columnClientFname;

    @FXML
    private TableColumn<DetailedResolution, String> columnClientLname;

    @FXML
    private TableColumn<DetailedResolution, Date> columnDate;

    @FXML
    private TableColumn<DetailedResolution, String> columnDetail;

    @FXML
    private TableColumn<DetailedResolution, String> columnDetailName;

    @FXML
    private TableColumn<DetailedResolution, String> columnDetailType;

    @FXML
    private TableColumn<DetailedResolution, String> columnResult;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuff;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuffEmail;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuffFname;

    @FXML
    private TableColumn<DetailedResolution, String> columnStuffLname;

    @FXML
    private TableView<DetailedResolution> infoTable;

    //endregion

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @FXML
    private Button makeGeneralReportBtn;
    @FXML
    private Button makeReport;
    @FXML
    private Button backButton;


    ObservableList<DetailedResolution> resolutionList;

    @FXML
    void onBackButton() throws IOException {
        new AdminViewController().show((Stage) backButton.getScene().getWindow());
    }

    @FXML
    void onMakeGeneralReportBtn() throws IOException {
    }

    @FXML
    void onMakeReportButton() {
        //todo datepicker
    }


    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "adminViews/admin-create-report-view.fxml", "Создание отчёта");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        columnStuffLname.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("stuffLname"));
        columnStuffFname.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("stuffFname"));
        columnStuffEmail.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("stuffEmail"));
        columnClientLname.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("clientLname"));
        columnClientFname.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("clientFname"));
        columnClientEmail.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("clientEmail"));
        columnResult.setCellValueFactory(new PropertyValueFactory<DetailedResolution, String>("result"));
        columnDate.setCellValueFactory(new PropertyValueFactory<DetailedResolution, Date>("date"));
        infoTable.setItems(resolutions);
    }
}
