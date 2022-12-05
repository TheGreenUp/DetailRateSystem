package com.bsuir.green.controller.Admin;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.AddStuffCommand;
import com.bsuir.green.common.command.DeleteStuffCommand;
import com.bsuir.green.common.command.StuffListCommand;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.DeleteStuffResponse;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.StuffCreationResponse;
import com.bsuir.green.common.response.StuffListResponse;
import com.bsuir.green.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminStuffTableController implements Initializable {

    //region textfields
    @FXML
    private TextField lname;
    @FXML
    private TextField fname;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    //endregion
    @FXML
    private ComboBox cbGetRoles;
    ObservableList<Stuff> stufflist;
    int chosenStuffIndex = -1;
    Stuff currentStuff = null;
    int chosenStuffId = -1; //todo эт косяк, но я хз как его править. Надо из таблицы получать ID, но выдавать ID для регистрации такое себе

    //region table
    @FXML
    private TableView<Stuff> stuffTable;
    @FXML
    private TableColumn<Stuff, Integer> columnId;
    @FXML
    private TableColumn<Stuff, String> columnLname;
    @FXML
    private TableColumn<Stuff, String> columnFname;
    @FXML
    private TableColumn<Stuff, String> columnEmail;
    @FXML
    private TableColumn<Stuff, String> columnPassword;
    @FXML
    private TableColumn<Stuff, Integer> columnRole;
    //endregion
    //region buttons
    @FXML
    Button addStuffButton;
    @FXML
    Button exitButton;
    @FXML
    Button deleteStuffButton;

    //endregion
    public void show(Stage stage, Stuff stuff) throws IOException {
        ViewUtils.loadView(stage, "admin-stuff-table-view.fxml", "Управление сотрудниками");
        currentStuff = stuff;
    }
    public void onAddStuffButton() throws IOException {
        AddStuffCommand addStuffCommand = new AddStuffCommand(new Stuff(lname.getText(), fname.getText(), email.getText(), password.getText()));
        //todo возможность добавлять администраторов
        Client.writeObject(addStuffCommand);
        Object response = Client.readObject();
        if (response instanceof StuffCreationResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {

        } else {
            //просто ошибка
        }
    }

    public void onExitButton() throws IOException {
        new AdminViewController().show((Stage) exitButton.getScene().getWindow(), currentStuff);

    }

    public void onDeleteStuffButton() throws IOException {
        DeleteStuffCommand deleteStuffCommand = new DeleteStuffCommand(
                new Stuff(chosenStuffId, (Integer) cbGetRoles.getValue(),
                        lname.getText(), fname.getText(),
                        email.getText(), password.getText()));
        Client.writeObject(deleteStuffCommand);
        Object response = Client.readObject();
        if (response instanceof DeleteStuffResponse) {
            //todo обновить таблицу
            refreshTable();
        } else if (response instanceof ErrorResponse) {
            //todo sout error response
        } else {
            //todo log.error
        }
    }

    @FXML
    void getSelected(MouseEvent mouseEvent) {
        chosenStuffIndex = stuffTable.getSelectionModel().getSelectedIndex();
        if (chosenStuffIndex <= -1) {//проверка на то, выделил ли что-нибудь пользователь или нет
            return;
        }
        lname.setText(columnLname.getCellData(chosenStuffIndex));
        fname.setText(columnFname.getCellData(chosenStuffIndex));
        email.setText(columnEmail.getCellData(chosenStuffIndex));
        password.setText(columnPassword.getCellData(chosenStuffIndex));
        cbGetRoles.setValue((int) columnRole.getCellData(chosenStuffIndex));
        chosenStuffId = columnId.getCellData(chosenStuffIndex);
    }

    private void setCellTable(ObservableList<Stuff> stufflist) {
        columnId.setCellValueFactory(new PropertyValueFactory<Stuff, Integer>("id"));
        columnFname.setCellValueFactory(new PropertyValueFactory<Stuff, String>("fname"));
        columnLname.setCellValueFactory(new PropertyValueFactory<Stuff, String>("lname"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Stuff, String>("email"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Stuff, String>("password"));
        columnRole.setCellValueFactory(new PropertyValueFactory<Stuff, Integer>("role"));
        stuffTable.setItems(stufflist);
    }

    private void refreshTable() {//todo тут одинаковый код с init, но я хз как поправить, ибо при удалении и добавлении хотелось бы, чтобы сразу таблица обновлялась
        StuffListCommand stuffListCommand = new StuffListCommand();
        Client.writeObject(stuffListCommand);
        Object response = Client.readObject();
        if (response instanceof StuffListResponse) {
            stufflist = FXCollections.observableList((List<Stuff>) ((StuffListResponse) response).getStuff());
            setCellTable(stufflist);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StuffListCommand stuffListCommand = new StuffListCommand();
        Client.writeObject(stuffListCommand);
        Object response = Client.readObject();
        if (response instanceof StuffListResponse) {
            stufflist = FXCollections.observableList((List<Stuff>) ((StuffListResponse) response).getStuff());
            setCellTable(stufflist);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }

}