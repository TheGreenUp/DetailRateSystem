package com.bsuir.green.service;


import com.bsuir.green.common.command.LoginCommand;
import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.ErrorResponse;
import com.bsuir.green.common.response.LoginResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.database.dao.ClientDao;
import com.bsuir.green.database.dao.StuffDao;
import com.bsuir.green.exception.UserNotFoundException;

import java.sql.SQLException;

public class LoginService {

    private final StuffDao stuffDao;
    private static final LoginService loginService = new LoginService();

    private LoginService() {
        stuffDao = StuffDao.getInstance();
    }

    public static LoginService getInstance() {
        return loginService;
    }

    public Response login(LoginCommand loginCommand) throws SQLException {
        try {
            Stuff stuff = stuffDao.getByEmailAndPassword(loginCommand.getName(), loginCommand.getPassword());
            return new LoginResponse(stuff, null);
        } catch (UserNotFoundException stuffNotFoundException) {
            try {
                Client client = ClientDao.getInstance().getByEmailAndPassword(loginCommand.getName(), loginCommand.getPassword());
                return new LoginResponse(null,client);
            } catch (UserNotFoundException clientNotFoundException) {
                return new ErrorResponse("Пользователь не найден");
            }
            //return new ErrorResponse("Пользователь не найден");
        }
    }
}
