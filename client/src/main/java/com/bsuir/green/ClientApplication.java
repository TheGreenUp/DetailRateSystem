package com.bsuir.green;

import com.bsuir.green.utils.ViewUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "hello-view.fxml", "Добро пожаловать");

    }

    public static void main(String[] args) {
        launch();
    }
}