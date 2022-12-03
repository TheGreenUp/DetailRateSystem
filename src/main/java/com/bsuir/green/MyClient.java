//package com.bsuir.green;
//
//import com.bsuir.green.menu.ClientMenu;
//import com.bsuir.green.menu.StuffMenu;
//import com.bsuir.green.models.Stuff;
//import com.bsuir.green.utils.Helper;
//import common.model.Stuff;
//
//import java.io.*;
//import java.net.Socket;
//
//
//public class MyClient {
//
//    public static void main(String[] arg) {
//        boolean exit = false;
//        int chosenAuthOption;
//        try {
//            //region Установление подключения
//            System.out.println("Подключение к серверу....");
//            Socket clientSocket = new Socket("127.0.0.1", 2525);//установление //соединения между локальной машиной и указанным портом узла сети
//            System.out.println("Подключение установлено....");
//            //endregion
//
//            //region Инициализация переменных
//            BufferedReader stdin =
//                    new BufferedReader(new InputStreamReader(System.in));//создание буферизированного символьного потока ввода
//            ObjectOutputStream toServer =
//                    new ObjectOutputStream(clientSocket.getOutputStream());//создание потока вывода
//            ObjectInputStream fromServer =
//                    new ObjectInputStream(clientSocket.getInputStream());//создание потока ввода
//            //endregion
//            Helper helper = new Helper();
//
//            while (!exit) {
//                System.out.println((String)fromServer.readObject());//получаем текст менюшки от сервера
//
//                chosenAuthOption = Integer.parseInt(stdin.readLine());
//                toServer.writeObject(chosenAuthOption);
//
//                switch (chosenAuthOption) {
//                    case 1 -> {
//                        System.out.println("Введите e-mail: ");
//                        String clientEmail = stdin.readLine();
//                        System.out.println("Введите пароль: ");
//                        String clientPassword = stdin.readLine();
//                        toServer.writeObject(clientEmail);//потоку вывода присваивается значение строковой переменной (передается серверу)
//                        toServer.writeObject(clientPassword);//потоку вывода присваивается значение строковой переменной (передается серверу)
//                        Object o = fromServer.readObject();
//                        if (o instanceof com.bsuir.green.models.Client) {
//
//                            com.bsuir.green.models.Client client = (com.bsuir.green.models.Client) o;//из полученных данных разбираем нужное
//                            System.out.println((String) fromServer.readObject());//приветственное сообщение
//
//                            ClientMenu.clientMenu(client,toServer,fromServer);
//
//                        } else if (o instanceof com.bsuir.green.models.Stuff) {
//                            Stuff stuff = (Stuff) o;//из полученных данных разбираем нужное
//                            System.out.println((String) fromServer.readObject());//приветственное сообщение
//                            if (stuff.getRole() == 0) {
//                                StuffMenu.adminMenu(stuff,toServer,fromServer);
//                            }
//                            else {
//                                StuffMenu.stuffMenu(stuff, toServer,fromServer);
//                            }
//                        } else {
//                            System.out.println("Ошибка!");
//                        }
//                    }
//                    case 2 -> {
//                        System.out.println("Введите ваше имя: ");
//                        String clientFName = stdin.readLine();
//                        System.out.println("Введите вашу фамилию: ");
//                        String clientLName = stdin.readLine();
//                        System.out.println("Введите e-mail: ");
//                        String clientEmail = stdin.readLine();
//                        System.out.println("Введите пароль: ");
//                        String clientPassword = stdin.readLine();
//                        com.bsuir.green.models.Client client = helper.createClient(clientFName, clientLName, clientEmail, clientPassword);
//                        toServer.writeObject(client);
//                    }
//                    case 3 -> {
//                        System.out.println((String) fromServer.readObject());
//                        exit = true;
//                    }
//                    default -> {
//                        System.out.println("Что-то не так ёпта)");
//                    }
//                }
//            }
//            toServer.close();//закрытие потока вывода
//            fromServer.close();//закрытие потока ввода
//            clientSocket.close();//закрытие сокета
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();//выполнение метода исключения е
//        }
//    }
//
//}
