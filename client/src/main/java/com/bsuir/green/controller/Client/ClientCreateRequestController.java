package com.bsuir.green.controller.Client;

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
import java.util.ResourceBundle;

public class ClientCreateRequestController implements Initializable {
    @FXML
    TextField detailName;
    @FXML
    ComboBox<DetailType> cbDetailType = new ComboBox();
    @FXML
    ComboBox cbChosenStuff = new ComboBox<>();
    @FXML
    Button backButton;


    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "client-make-request.fxml", "Создание запроса");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          cbDetailType.getItems().addAll(DetailType.values());//закидываем значения в комбобокс с типами деталей
    }
    public void onCreateRequestButton(){
      //  Request request = new Request(detailName.getText(),cbDetailType.getTypeSelector(),cbChosenStuff.getTypeSelector());
        //todo как отобразить специалиста в комбобоксе
       // CreateRequestCommand requestCommand = new CreateRequestCommand();
    }
    public void onBackButton() throws IOException{
        new ClientViewController().show((Stage) backButton.getScene().getWindow());
    }
}
