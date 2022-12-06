package com.bsuir.green.service;

import com.bsuir.green.common.command.*;
import com.bsuir.green.common.command.createCommands.CreateRequestCommand;
import com.bsuir.green.common.command.getCommands.GetAllClientDetailsCommand;
import com.bsuir.green.common.command.getCommands.GetClientIdFromRequestCommand;
import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.model.RequestForStuff;
import com.bsuir.green.common.response.*;
import com.bsuir.green.database.dao.RequestDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RequestService {
    private final RequestDao requestDao;
    private static final RequestService requestService = new RequestService();

    private RequestService() {
        requestDao = RequestDao.getInstance();
    }

    public static RequestService getInstance() {
        return requestService;
    }

    public Response getRequests() throws SQLException {
        ArrayList<Request> all = requestDao.getAll();
        return new RequestListResponse(all);
    }
    public Response getRequestsForStuff(RequestListForStuffCommand requestListForStuffCommand) throws SQLException {
        List<RequestForStuff> all = requestDao.getInfoForStuff(requestListForStuffCommand.getStuff().getId());
        return new RequestListForStuffResponse(all);
    }
    public Response getClientByRequestId(GetClientIdFromRequestCommand getClientIdFromRequestCommand)throws SQLException {
        Client client = requestDao.getClientByRequest(getClientIdFromRequestCommand);
        return new GetClientIdFromRequestResponse(client);

    }
    public Response createRequest(CreateRequestCommand сreateRequestCommand) throws SQLException {
        requestDao.createRequest(сreateRequestCommand.getRequest());
        return new CreateRequestResponse();
    }
    public Response uptadeRequestStatus(UpdateRequestStatusCommand updateRequestStatusCommand) throws SQLException {
        requestDao.updateRequestStatus(updateRequestStatusCommand.getRequestId());
        return new UpdateRequestStatusResponse();
    }
    public Response getAllClientDetails(GetAllClientDetailsCommand getAllClientDetailsCommand) throws SQLException {
        return new GetAllClientDetailsResponse(
                requestDao.getAllClientDetails(getAllClientDetailsCommand.getClient()));

    }

}
