package com.bsuir.green.controller.Admin;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.createCommands.AddStuffCommand;
import com.bsuir.green.common.command.DeleteStuffCommand;
import com.bsuir.green.common.command.listCommands.StuffListCommand;
import com.bsuir.green.common.command.UpdateStuffCommand;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.*;
import com.bsuir.green.common.response.createResponse.StuffCreationResponse;
import com.bsuir.green.common.response.listRepsonse.StuffListResponse;
import com.bsuir.green.common.utils.Helper;
import com.bsuir.green.enums.StuffRoles;
import com.bsuir.green.utils.DialogUtils;
import com.bsuir.green.utils.Validator;
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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
    int chosenStuffId = -1;

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
    @FXML
    Button updateButton;

    //endregion
    public void show(Stage stage, Stuff stuff) throws IOException {
        ViewUtils.loadView(stage, "adminViews/admin-stuff-table-view.fxml", "Управление сотрудниками");
        currentStuff = stuff;
    }
    public void onAddStuffButton() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {;
        if (!Validator.emailValidation(email.getText())) {
            DialogUtils.showError("Неправильный email", "Ошибка!");
            return;
        }
        //todo check existing
        AddStuffCommand addStuffCommand =
                new AddStuffCommand(
                        new Stuff(lname.getText(), fname.getText(),
                                email.getText(), Helper.getInstance().getPasswordHash(password.getText()),
                                getIntRoleByComboBox(cbGetRoles)));
        Client.writeObject(addStuffCommand);
        Object response = Client.readObject();
        if (response instanceof StuffCreationResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {

        } else {
            //просто ошибка
        }
    }
    public void onUpdateButton() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        UpdateStuffCommand updateStuffCommand =
                new UpdateStuffCommand(new Stuff(
                                chosenStuffId,getIntRoleByComboBox(cbGetRoles),
                                lname.getText(), fname.getText(),
                                email.getText(), Helper.getInstance().getPasswordHash(password.getText())));
        Client.writeObject(updateStuffCommand);
        Object response = Client.readObject();
        if (response instanceof UpdateStuffResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {

        } else {
            //просто ошибка
        }
    }
    public void onExitButton() throws IOException {
        new AdminViewController().show((Stage) exitButton.getScene().getWindow(), currentStuff);

    }

    public void onDeleteStuffButton() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        if (!DialogUtils.deleteConfirmation()) return;
        DeleteStuffCommand deleteStuffCommand = new DeleteStuffCommand(
                new Stuff(chosenStuffId, getIntRoleByComboBox(cbGetRoles),
                        lname.getText(), fname.getText(),
                        email.getText(), Helper.getInstance().getPasswordHash(password.getText())));
        Client.writeObject(deleteStuffCommand);
        Object response = Client.readObject();
        if (response instanceof DeleteStuffResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {
            DialogUtils.showError(((ErrorResponse) response).getErrorMessage(), "Ошибка!");
        } else {
            System.out.println("Unknown error");
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
        cbGetRoles.setValue(getStringRoleByComboBox ((int) columnRole.getCellData(chosenStuffIndex)));
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

    private void refreshTable() {
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
        cbGetRoles.getItems().addAll(StuffRoles.getLables());//закидываем значения в комбобокс с типами деталей
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

    public int getIntRoleByComboBox(ComboBox cbGetRoles){
        switch ((String) cbGetRoles.getValue()) {
            case "Администратор" -> {
                return 0;
            }
            case "Специалист" -> {
                return 1;
            }
        }
        return -1;
    }
    public String getStringRoleByComboBox(int chosenRole ) {
        switch (chosenRole) {
            case 0 -> {
                return "Администратор";
            }
            case 1 -> {
                return "Специалист";
            }
        }
        return null;
    }

}
