package com.bsuir.green.utils;

import com.bsuir.green.models.Client;

public class Helper {
    public Client createClient(String fname, String lname, String email, String password){
        Client client = new Client();
        client.setFname(fname);
        client.setLname(lname);
        client.setEmail(email);
        client.setPassword(password);
        return client;
    }
}
