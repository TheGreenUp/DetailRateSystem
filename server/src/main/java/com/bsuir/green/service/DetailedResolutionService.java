package com.bsuir.green.service;

import com.bsuir.green.common.command.GetClientDetailedResolutionCommand;
import com.bsuir.green.common.command.getCommands.GetDetailedResolutionBetweenDatesCommand;
import com.bsuir.green.common.response.getResponse.GetClientDetailedResResponse;
import com.bsuir.green.common.response.getResponse.GetDetailedResolutionBetweenDateResponse;
import com.bsuir.green.common.response.getResponse.GetDetailedResolutionResponse;
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

    public Response getResolution() throws SQLException {
        return new GetDetailedResolutionResponse(DetailedResolutionDao.getInstance().getResolution());
    }
    public Response getClientResolution(GetClientDetailedResolutionCommand command) throws SQLException {
        return new GetClientDetailedResResponse(DetailedResolutionDao.getInstance().getClientResolution(command));
    }
    public Response getResolutionBetweenDates(GetDetailedResolutionBetweenDatesCommand command) throws SQLException {
       return new GetDetailedResolutionBetweenDateResponse(DetailedResolutionDao.getInstance().getResolutionBetweenDate(command));

    }


}
