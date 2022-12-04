package com.bsuir.green.utils;

import com.bsuir.green.common.model.*;
import com.bsuir.green.enums.DetailType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    public String defineUser(ResultSet rs) {
        ResultSetMetaData resultSetMetaData = null;
        try {
            resultSetMetaData = rs.getMetaData();
            return resultSetMetaData.getTableName(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Stuff createStuff(ResultSet rs) {
        Stuff stuff = new Stuff();
        try {
            stuff.setFname(rs.getString("fname"));
            stuff.setId(rs.getInt("id"));
            stuff.setLname(rs.getString("lname"));
            stuff.setPassword(rs.getString("password"));
            stuff.setRole(rs.getInt("role"));
            stuff.setEmail(rs.getString("email"));
            return stuff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client createClient(ResultSet rs) {
        Client client = new Client();
        try {
            client.setFname(rs.getString("fname"));
            client.setId(rs.getInt("id"));
            client.setLname(rs.getString("lname"));
            client.setPassword(rs.getString("password"));
            client.setEmail(rs.getString("email"));
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendError(ObjectOutputStream toClient) {
        try {
            toClient.writeObject(new IOException());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String chooseDetailType(ObjectOutputStream toClient, ObjectInputStream fromClient) {
        int i = 1, chosenDetailType;
        ArrayList dtypesArray = new ArrayList<>();
        try {
            for (DetailType dt : DetailType.values()) {//выводим всех
                //toClient.writeObject("[" + i + "]" + " " + dt.label);
                dtypesArray.add(dt.typeOfDetail);
                i++;
            }
            toClient.writeObject(dtypesArray);
            chosenDetailType = (int) fromClient.readObject();//ждем нужного от клиента
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        chosenDetailType--;
        return DetailType.values()[chosenDetailType].typeOfDetail;//возвращаем нужное
    }

    public Resolution createResolution(Request request, Detail detail, int finalRate){
        String result = "Сертифицирована";
        if (finalRate <= 20) result = "Не подлежит сертификации";

        Date date = Date.valueOf(LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return new Resolution(request.getId(), detail.getId(),request.getClient_id(), result, date);
    }
    public Stuff createStuff(){
        String lname, fname, email, password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию работника: ");
        lname = scanner.nextLine();
        System.out.println("Введите имя работника: ");
        fname = scanner.nextLine();
        System.out.println("Введите e-mail работника:");
        email = scanner.nextLine();
        System.out.println("Введите пароль для входа работника: ");
        password = scanner.nextLine();
        return new Stuff();
    }



}
