package com.bsuir.green.controller;

import com.bsuir.green.utils.ViewUtils;
import javafx.stage.Stage;

import java.io.IOException;

public class StuffViewController {
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuff-view.fxml", "Работка");
    }
}
