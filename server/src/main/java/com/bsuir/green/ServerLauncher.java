package com.bsuir.green;

public class ServerLauncher {

    public static void main(String[] args) throws Exception {
        //todo pass port as parameter
        new Server().launch(2525);
    }
}
