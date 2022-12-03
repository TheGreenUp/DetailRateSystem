package com.bsuir.green.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button logInButton;

//    public void onLoginButton() {
//        NMClient.writeObject(emailField.getText(), passwordField.getText());//потоку вывода присваивается значение строковой переменной (передается серверу)
//        Object o = fromServer.getObject();
//
//        if (o instanceof Client) {
//            Client client = (Client) o;//из полученных данных разбираем нужное
//            System.out.println((String) fromServer.readObject());//приветственное сообщение
//            new ClientMenu.clientMenu(client, toServer, fromServer);
//        } else if (o instanceof Stuff) {
//            Stuff stuff = (Stuff) o;//из полученных данных разбираем нужное
//            System.out.println((String) fromServer.readObject());//приветственное сообщение
//            if (stuff.getRole() == 0) {
//                StuffMenu.adminMenu(stuff, toServer, fromServer);
//            } else {
//                StuffMenu.stuffMenu(stuff, toServer, fromServer);
//            }
//        } else {
//            System.out.println("Ошибка!");
//        }
//    }

}

