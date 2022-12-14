package com.bsuir.green.service;

import com.bsuir.green.common.command.createCommands.CreateDetailCommand;
import com.bsuir.green.common.command.getCommands.GetDetailCommand;
import com.bsuir.green.common.response.createResponse.CreateDetailResponse;
import com.bsuir.green.common.response.getResponse.GetDetailResponse;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.database.dao.DetailDao;

import java.sql.SQLException;

public class DetailService {
    private final DetailDao detailDao;
    private static final DetailService detailService = new DetailService();

    private DetailService() {
        detailDao = DetailDao.getInstance();
    }

    public static DetailService getInstance() {
        return detailService;
    }

    public Response createDetail(CreateDetailCommand createDetailCommand) throws SQLException {
        detailDao.createDetail(createDetailCommand.getDetail());
        return new CreateDetailResponse();
    }
    public Response getDetail(GetDetailCommand getDetailCommand) throws SQLException {
        return new GetDetailResponse(detailDao.getDetail(getDetailCommand.getDetail()));

    }
}
