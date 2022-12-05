package com.bsuir.green.service;

import com.bsuir.green.common.command.CreateDetailCommand;
import com.bsuir.green.common.command.GetDetailCommand;
import com.bsuir.green.common.response.CreateDetailResponse;
import com.bsuir.green.common.response.GetDetailResponse;
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
