package com.bsuir.green.service;


import com.bsuir.green.common.command.getCommands.GetResolutionCommand;
import com.bsuir.green.common.command.MakeResolutionCommand;
import com.bsuir.green.common.response.GetResoutionResponse;
import com.bsuir.green.common.response.MakeResolutionResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.database.dao.ResolutionDao;

import java.sql.SQLException;

public class ResolutionService {
    private final ResolutionDao resolutionDao;
    private static final ResolutionService resolutionService = new ResolutionService();

    public static ResolutionService getInstance() {
        return resolutionService;
    }

    private ResolutionService() {
        resolutionDao = ResolutionDao.getInstance();
    }


    public Response addResolution(MakeResolutionCommand makeResolutionCommand) throws SQLException {
        resolutionDao.createResolution(makeResolutionCommand.getResolution());
        return new MakeResolutionResponse();
    }

    public Response getResolution(GetResolutionCommand getResolutionCommand) throws SQLException {
        return new GetResoutionResponse(resolutionDao.getResolution(getResolutionCommand));

    }


}
