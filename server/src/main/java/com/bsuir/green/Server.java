package com.bsuir.green;

import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.service.CommandManager;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

@Slf4j
public class Server {

    public void launch(int port) throws IOException {
        //todo limit threads
        var serverSocket = new ServerSocket(port);
        log.info("Server started");
        while (true)
            new ClientHandler(serverSocket.accept()).start();
    }

    public static class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            log.info("Start handling client");
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception e) {
                log.error("Failed to connect", e);
                return;
            }

            while (true) {
                try {
                    Object commandObject = in.readObject();
                    CommandDto commandDto = (CommandDto) commandObject;
                    Response response = CommandManager.getInstance().execute(commandDto);
                    out.writeObject(response);
                } catch (SocketException e) {
                    log.error("Connection failed", e);
                    return;
                } catch (Exception e) {
                    log.error("Got an error while processing client request", e);
                    try {
                        out.writeObject(new ErrorResponse(e.getMessage()));
                    } catch (IOException ex) {
                        log.error("Failed to send error response to client", e);
                    }
                }
            }
        }
    }
}
