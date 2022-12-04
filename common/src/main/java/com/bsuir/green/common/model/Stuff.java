package com.bsuir.green.common.model;

import java.io.Serializable;
public class Stuff implements Serializable {
    //region Переменные
    int id, role;
    String lname, fname, email, password;
    //endregion

    //region ГеттерыСеттеры
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "Работник " + lname  + " " + fname;
    }

    public Stuff() {
    }

    public Stuff(int id, int role, String lname, String fname, String email, String password) {
        this.id = id;
        this.role = role;
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.password = password;
    }

    public Stuff(String lname, String fname, String email, String password) {
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.password = password;
    }
}
