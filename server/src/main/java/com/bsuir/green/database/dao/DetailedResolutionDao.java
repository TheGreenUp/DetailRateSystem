package com.bsuir.green.database.dao;

import com.bsuir.green.common.model.DetailedResolution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailedResolutionDao {
    private static final DetailedResolutionDao detailedResolutionDao = new DetailedResolutionDao();
    private final ConnectionManager connectionManager;

    public static DetailedResolutionDao getInstance() {
        return detailedResolutionDao;
    }

    private DetailedResolutionDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    private static final String GET_RESOLUTIONS = "SELECT * FROM autosalon.resolution\n" +
            "JOIN autosalon.request ON request_id = request.id " +
            "JOIN autosalon.stuff ON stuff_id = stuff.id " +
            "JOIN autosalon.detail ON request_detail_id = autosalon.detail.id " +
            "JOIN autosalon.client ON request_client_id = autosalon.client.id;";

    public ArrayList<DetailedResolution> getResolution() throws SQLException {
        ArrayList<DetailedResolution> resolutions = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_RESOLUTIONS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetailedResolution resolution =
                        new DetailedResolution(
                                resultSet.getString("type"),
                                resultSet.getString("name"),
                                resultSet.getString("stuffName"),
                                resultSet.getString("stuffSurname"),
                                resultSet.getString("stuffEmail"),
                                resultSet.getString("clientName"),
                                resultSet.getString("clientSurname"),
                                resultSet.getString("clientEmail"),
                                resultSet.getString("result"),
                                resultSet.getDate("date"));
                resolutions.add(resolution);

            }
            return resolutions;
        }
    }

}
