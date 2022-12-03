package com.bsuir.green.menu;

import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.model.Stuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class ClientMenu {
    public static void clientMenu(Client client, ObjectOutputStream toServer, ObjectInputStream fromServer) {
        int chosenMenuOption, chosenDetailType, chosenSpecialist;
        int sequenceNumber = 1; //чтобы отображались заказы и специалисты с 1, а не нуля
        String detailName;
        boolean exitFromAcc = false;
        ArrayList<String> dtypesArray = new ArrayList<>();
        ArrayList<Stuff> specialists = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
            while (!exitFromAcc) {
                System.out.println((String) fromServer.readObject());
                chosenMenuOption = Integer.parseInt(scanner.nextLine());
                toServer.writeObject(chosenMenuOption);
                switch (chosenMenuOption) {
                    case 1 -> {
                        System.out.println((String) fromServer.readObject()); //Выберите тип детали
                        dtypesArray = (ArrayList<String>) fromServer.readObject();
                        for (int i = 0; i < dtypesArray.size(); i++) {
                            System.out.println("[" + sequenceNumber + "] " + dtypesArray.get(i));
                            sequenceNumber++;//чтобы отображались заказы и специалисты с 1, а не нуля
                        }
                        sequenceNumber = 1;//скидываем значение до начального
                        chosenDetailType = Integer.parseInt(scanner.nextLine());//выбрали тип детальки
                        toServer.writeObject(chosenDetailType);//отравили тип детали
                        System.out.println((String) fromServer.readObject());//"введите название детали"
                        detailName = scanner.nextLine();
                        toServer.writeObject(detailName);//отправить название детали
                        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                        System.out.println((String) fromServer.readObject());//"выберите специалиста"
                        specialists = (ArrayList<Stuff>) fromServer.readObject();
                        for (int i = 0; i < specialists.size(); i++) {
                            System.out.println("[" + sequenceNumber + "] " + specialists.get(i));
                            sequenceNumber++;
                        }
                        chosenSpecialist = Integer.parseInt(scanner.nextLine());
                        toServer.writeObject(chosenSpecialist);
                        System.out.println((String)fromServer.readObject());//Заказ принят!
                    }
                    case 2 -> {
                        ArrayList<Request> requests = (ArrayList<Request>) fromServer.readObject();//получаем с сервера массив реквестов
                        if (requests.isEmpty()) System.out.println("Нет заказов!");
                        else {
                            for (Request request : requests) {
                                System.out.println(request);
                            }
                        }
                    }

                    case 3 -> {
                        exitFromAcc = true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
