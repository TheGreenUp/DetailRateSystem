package com.bsuir.green.controller;


import com.bsuir.green.Client;
import com.bsuir.green.common.command.LoginCommand;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.LoginResponse;
import com.bsuir.green.controller.Admin.AdminViewController;
import com.bsuir.green.controller.Client.ClientViewController;
import com.bsuir.green.controller.Stuff.StuffViewController;
import com.bsuir.green.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UserLogInController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "hello-view.fxml", "Вход в систему");
    }

    public void onLoginButton() throws IOException {
        LoginCommand loginCommand = new LoginCommand(emailField.getText(), passwordField.getText());
        //LoginCommand loginCommand = new LoginCommand("daniilgreen@mail.ru", "12345678"); //1 админ
        //LoginCommand loginCommand = new LoginCommand("dan@gmail.com", "12345678"); //2 stuff
        //LoginCommand loginCommand = new LoginCommand("pasha@gmail.com", "1234"); //3 юзер
        Client.writeObject(loginCommand);
        Object response = Client.readObject();
        if (response instanceof LoginResponse) {
            if (((LoginResponse) response).getStuff() == null) {
                com.bsuir.green.common.model.Client client = ((LoginResponse) response).getClient();
                new ClientViewController().show((Stage) loginButton.getScene().getWindow(), client);

            } else {
                Stuff stuff = ((LoginResponse) response).getStuff();
                if (stuff.getRole() == 0) {
                    new AdminViewController().show((Stage) loginButton.getScene().getWindow(), stuff);
                } else {
                    new StuffViewController().show((Stage) loginButton.getScene().getWindow(), stuff);
                }
            }

        } else if (response instanceof ErrorResponse) {
            String errorMessage = ((ErrorResponse) response).getErrorMessage();
            log.error("Ошибка во время логина: {}", errorMessage);
        } else {
            log.error("Unknown response {}", response);
        }
    }

    public void onRegisterButton() throws IOException {
        new UserRegisterController().show((Stage) loginButton.getScene().getWindow());
    }


}

