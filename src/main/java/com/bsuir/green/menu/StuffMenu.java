package com.bsuir.green.menu;

import com.bsuir.green.models.Request;
import com.bsuir.green.models.Stuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StuffMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void stuffMenu(Stuff stuff, ObjectOutputStream toServer, ObjectInputStream fromServer) {
        int chosenOption;
        boolean exitFromAcc = false;

        ArrayList<Request> requests = new ArrayList<>();
        ArrayList<String> questionsForRate = new ArrayList<>();
        while (!exitFromAcc) {
            try {
                System.out.println((String)fromServer.readObject());//Ловим менюшку от сервера
                chosenOption = Integer.parseInt(scanner.nextLine());
                toServer.writeObject(chosenOption);
                switch (chosenOption) {
                    case 1 -> {
                        int rate, finalRate = 0;
                        printRequests(fromServer, requests);//получаем с сервера все наши запросы
                        int chosenDetailId = Integer.parseInt(scanner.nextLine());
                        toServer.writeObject(chosenDetailId);//отправляем на сервер выбранную нами деталь
                        questionsForRate = (ArrayList<String>) fromServer.readObject();//получаем список вопросов для оценки
                        for (String question: questionsForRate) {
                            System.out.println(question);
                            rate = Integer.parseInt(scanner.nextLine());
                            finalRate+=rate;
                        }
                        toServer.writeObject(finalRate);


                    }
                    case 2 -> {
                       printRequests(fromServer, requests);
                    }
                    case 3 -> {
                        exitFromAcc = true;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + chosenOption);
                }
            }
            catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void adminMenu(Stuff stuff, ObjectOutputStream toServer, ObjectInputStream fromServer) {
        int chosenOption;
        boolean exitFromAcc = false;

        ArrayList<Request> requests = new ArrayList<>();
        ArrayList<String> questinonsForCreateStuff = new ArrayList<>();
        while (!exitFromAcc) {
            try {
                System.out.println((String)fromServer.readObject());//Ловим менюшку от сервера
                chosenOption = Integer.parseInt(scanner.nextLine());
                toServer.writeObject(chosenOption);
                switch (chosenOption) {
                    case 1 -> {//add employe
                        System.out.println((String)fromServer.readObject());//введите фамилию
                        String lname = scanner.nextLine();
                        System.out.println((String)fromServer.readObject());//введите имя
                        String fname = scanner.nextLine();
                        System.out.println((String)fromServer.readObject());//введите email
                        String email = scanner.nextLine();
                        System.out.println((String)fromServer.readObject());//введите пароль
                        String password = scanner.nextLine();
                        toServer.writeObject(new Stuff(0,lname,fname,email,password));
                    }
                    case 2 -> {//delete employee

                    }
                    case 3 -> {//generate report
                    }
                    case 4 -> {
                        exitFromAcc = true;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + chosenOption);
                }
            }
            catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void printRequests(ObjectInputStream fromServer , ArrayList<Request> requests) {
        try {
            requests = (ArrayList<Request>) fromServer.readObject();
            for (Request rq: requests) {
                System.out.println(rq);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
