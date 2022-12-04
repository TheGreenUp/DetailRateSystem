package com.bsuir.green.service;

import com.bsuir.green.common.command.RegisterCommand;
import com.bsuir.green.common.response.RegisterResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.database.dao.ClientDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class ClientService {
    private final ClientDao clientDao;
    private static final ClientService clientService = new ClientService();

    private ClientService() {
        clientDao = ClientDao.getInstance();
    }

    public static ClientService getInstance() {
        return clientService;
    }

    public Response createUser(RegisterCommand registerCommand) throws SQLException {
        clientDao.createClient(registerCommand.getClient());
        return new RegisterResponse();
    }

}
