package com.bsuir.green.service;


import com.bsuir.green.common.command.*;
import com.bsuir.green.common.response.Response;

import java.sql.SQLException;

public class CommandManager {

    private static final CommandManager commandManager = new CommandManager();

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        return commandManager;
    }

    public Response execute(CommandDto commandDto) throws SQLException {
        Response response;
        switch (commandDto.getCommand()) {
            case LOGIN -> response = LoginService.getInstance().login((LoginCommand) commandDto);
            case REQUEST_LIST -> response = RequestService.getInstance().getRequests();
            case ADD_STUFF -> response = StuffService.getInstance().createStuff((AddStuffCommand) commandDto);
            case UPDATE_USER -> response = StuffService.getInstance().updateStuff((UpdateUserCommand) commandDto);
            case REGISTER-> response = ClientService.getInstance().createUser((RegisterCommand) commandDto);
            case STUFF_LIST-> response = StuffService.getInstance().getStuff();
            case DELETE_STUFF-> response = StuffService.getInstance().deleteStuff((DeleteStuffCommand) commandDto);
            case REQUEST_LIST_FOR_STUFF-> response = RequestService.getInstance().getRequestsForStuff((RequestListForStuffCommand)commandDto);
            default -> throw new RuntimeException("Команда не поддерживаестя " + commandDto.getCommand());
        }
        return response;
    }
}
