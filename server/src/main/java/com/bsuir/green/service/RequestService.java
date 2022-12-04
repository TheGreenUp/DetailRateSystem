package com.bsuir.green.service;

import com.bsuir.green.common.command.CreateRequestCommand;
import com.bsuir.green.common.command.RequestListForStuffCommand;
import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.model.RequestForStuff;
import com.bsuir.green.common.response.RequestListForStuffResponse;
import com.bsuir.green.common.response.RequestListResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.common.response.StuffCreationResponse;
import com.bsuir.green.database.dao.RequestDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
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

    public RequestListResponse getRequests() throws SQLException {
        List<Request> all = requestDao.getAll();
        return new RequestListResponse(all);
    }
    public RequestListForStuffResponse getRequestsForStuff(RequestListForStuffCommand requestListForStuffCommand) throws SQLException {
        List<RequestForStuff> all = requestDao.getInfoForStuff(requestListForStuffCommand.getStuff().getId());
        return new RequestListForStuffResponse(all);
    }

    public Response createRequest(CreateRequestCommand сreateRequestCommand) throws SQLException {
        requestDao.createRequest(сreateRequestCommand.getRequest());
        return new StuffCreationResponse();
    }

}
