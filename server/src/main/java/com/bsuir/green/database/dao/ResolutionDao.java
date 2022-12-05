package com.bsuir.green.database.dao;

import com.bsuir.green.common.command.GetResolutionCommand;
import com.bsuir.green.common.model.Resolution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResolutionDao {
    private static final ResolutionDao resolutionDao = new ResolutionDao();
    private final ConnectionManager connectionManager;

    public static ResolutionDao getInstance() {
        return resolutionDao;
    }

    private ResolutionDao() {
        connectionManager = ConnectionManager.getInstance();
    }


    public static final String ADD_RESOLUTION = "INSERT INTO" +
            " resolution(result,date,request_id,request_detail_id,request_client_id)" +
            " VALUES (?,?,?,?,?)";
    public static final String GET_RESOLUTION = "SELECT * FROM autosalon.resolution WHERE request_client_id = ?";

    public static final String GET_DETAIL_BY_RESOLUTION =
            "SELECT * FROM autosalon.resolution JOIN detail ON request_detail_id = detail.id WHERE resolution.id = ?";

    public void createResolution(Resolution resolution) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_RESOLUTION);
            statement.setString(1, resolution.getComment());
            statement.setDate(2, resolution.getDate());
            statement.setInt(3, resolution.getRequest_id());
            statement.setInt(4, resolution.getRequest_detail_id());
            statement.setInt(5, resolution.getRequest_client_id());
            statement.executeUpdate();
        }
    }

    public ArrayList<Resolution> getResolution(GetResolutionCommand getResolutionCommand) throws SQLException {
        ArrayList<Resolution> resolutions = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_RESOLUTION);
            statement.setInt(1, getResolutionCommand.getClient().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Resolution resolution = new Resolution(
                        resultSet.getInt("id"),
                        resultSet.getInt("request_id"),
                        resultSet.getInt("request_detail_id"),
                        resultSet.getInt("request_client_id"),
                        resultSet.getString("result"),
                        resultSet.getDate("date"));
                resolutions.add(resolution);

            }
            return resolutions;
        }
    }
}
