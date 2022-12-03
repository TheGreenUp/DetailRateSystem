package com.bsuir.green.controllers;

import com.bsuir.green.ClientApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientViewController {
    public void show(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главное меню");
        stage.setScene(scene);
        stage.show();
    }
}
