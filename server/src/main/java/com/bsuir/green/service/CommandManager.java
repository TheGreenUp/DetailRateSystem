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
            case REGISTER-> response = ClientService.getInstance().createUser((RegisterCommand) commandDto);
            case DELETE_STUFF-> response = StuffService.getInstance().deleteStuff((DeleteStuffCommand) commandDto);
            case STUFF_LIST-> response = StuffService.getInstance().getStuff();
            case REQUEST_LIST_FOR_STUFF-> response = RequestService.getInstance().getRequestsForStuff((RequestListForStuffCommand)commandDto);
            case REQUEST_LIST -> response = RequestService.getInstance().getRequests();
            case QUESTION_LIST-> response = RateService.getInstance().getQuestions((GetRateQuestionsCommand) commandDto);
            case ADD_STUFF -> response = StuffService.getInstance().createStuff((AddStuffCommand) commandDto);
            case CREATE_RESOLUTION-> response = ResolutionService.getInstance().addResolution((MakeResolutionCommand) commandDto);
            case CREATE_REQUEST-> response = RequestService.getInstance().createRequest((CreateRequestCommand) commandDto);
            case CREATE_DETAIL-> response = DetailService.getInstance().createDetail((CreateDetailCommand) commandDto);
            case UPDATE_USER -> response = StuffService.getInstance().updateStuff((UpdateUserCommand) commandDto);
            case UPDATE_REQUEST_STATUS-> response = RequestService.getInstance().uptadeRequestStatus((UpdateRequestStatusCommand) commandDto);
            case GET_CLIENT_ID_BY_REQUEST-> response = RequestService.getInstance().getClientByRequestId((GetClientIdFromRequestCommand) commandDto);
            case GET_DETAIL-> response = DetailService.getInstance().getDetail((GetDetailCommand) commandDto);
            default -> throw new RuntimeException("Команда не поддерживаестя " + commandDto.getCommand());
        }
        return response;
    }
}
