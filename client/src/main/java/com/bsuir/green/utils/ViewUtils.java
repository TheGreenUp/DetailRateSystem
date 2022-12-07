package com.bsuir.green.utils;

import com.bsuir.green.ClientApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {

    public static void loadView(Stage stage, String viewName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("/" + viewName));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public static void showPopup(String viewName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource(viewName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
