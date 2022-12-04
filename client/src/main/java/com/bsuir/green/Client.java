package com.bsuir.green;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {
    private static Client Client = new Client();
    //todo move to configs
    private int port = 2525;
    private String host = "localhost";
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Client() {
        try {
            connect(host, port);
        } catch (Exception e) {
            //todo beautify
            throw new RuntimeException("Failed to init connection to server");
        }
    }

    public void CloseSocket() throws IOException {
        socket.close();
    }

    public static Client getInstance() {
        return Client;
    }

    public static void writeObject(Object object) {
        try {
            Client.out.writeObject(object);
        } catch (IOException e) {
            //todo
            e.printStackTrace();
        }
    }

    public static Object readObject() {
        try {
            return Client.in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //todo
            e.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

}
