package com.bsuir.green.common.model;


import java.io.Serializable;


public class Client implements Serializable {

    //region Переменные
    int id;
    String lname, fname, email, password;
    //endregion

    //region Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

    public Client(int id, String lname, String fname, String email, String password) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.password = password;
    }

    public Client(String lname, String fname, String email, String password) {
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.password = password;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return "Клиент: " + lname  + fname +
                "\n, e-mail: " + email +
                "\nПароль: "+ password;
    }


}
