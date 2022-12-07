package com.bsuir.green.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtils {

    public static void showAlert(String errorMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setResizable(true);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void showError(String errorMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setResizable(true);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void showOk(String contentText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setResizable(true);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    public static boolean deleteConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Увольнение");
        alert.setResizable(true);
        alert.setContentText("Вы уверены, что хотите уволить этого сотрудника?");
        Optional <ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

}
