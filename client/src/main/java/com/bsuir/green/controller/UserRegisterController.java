package com.bsuir.green.controller;

import com.bsuir.green.Client;
import com.bsuir.green.common.command.RegisterCommand;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.RegisterResponse;
import com.bsuir.green.common.utils.Helper;
import com.bsuir.green.utils.DialogUtils;
import com.bsuir.green.utils.Validator;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class UserRegisterController {

    @FXML
    private TextField lname;
    @FXML
    private TextField fname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;
    @FXML
    private Button exitFromFormButton;
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "register-view.fxml", "Регистрация");
    }
    public void onExitButton() throws  IOException{
        new UserLogInController().show((Stage) exitFromFormButton.getScene().getWindow());
    }
    public void onRegisterButton() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        if (!Validator.emailValidation(email.getText())) {
            DialogUtils.showError("Неправильный ввод!", "Ошибка!");
            return;
        }
        RegisterCommand registerCommand = new RegisterCommand(
                new com.bsuir.green.common.model.Client(
                        fname.getText(),lname.getText(),email.getText(),
                        Helper.getInstance().getPasswordHash(password.getText())));
        Client.writeObject(registerCommand);
        Object response = Client.readObject();
        if (response instanceof RegisterResponse) {
            DialogUtils.showOk("Успешная регистрация!", "Успех!");
            onExitButton();
        }
        else if (response instanceof ErrorResponse) {
            //знаем какая ошибка
        }
        else {
            //просто ошибка
        }
    }
}
