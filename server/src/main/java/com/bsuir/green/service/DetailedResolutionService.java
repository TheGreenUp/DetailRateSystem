package com.bsuir.green.service;

import com.bsuir.green.common.command.getCommands.GetDetailedResolutionCommand;
import com.bsuir.green.common.response.GetDetailedResolutionResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.database.dao.DetailedResolutionDao;

import java.sql.SQLException;

public class DetailedResolutionService {

    private final DetailedResolutionDao detailedResolutionDao;
    private static final DetailedResolutionService detailedResolutionService = new DetailedResolutionService();

    public static DetailedResolutionService getInstance() {
        return detailedResolutionService;
    }

    private DetailedResolutionService() {
        detailedResolutionDao = DetailedResolutionDao.getInstance();
    }

    public Response getResolution(GetDetailedResolutionCommand getDetailedResolutionCommand) throws SQLException {
        return new GetDetailedResolutionResponse(DetailedResolutionDao.getInstance().getResolution());

    }


}
