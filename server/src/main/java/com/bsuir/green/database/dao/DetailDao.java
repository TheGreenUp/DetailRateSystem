package com.bsuir.green.database.dao;

import com.bsuir.green.common.model.Detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailDao {
    private static final DetailDao detailDao = new DetailDao();
    public static DetailDao getInstance() {
        return detailDao;
    }
    private final ConnectionManager connectionManager;
    private DetailDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    public static final String CREATE_DETAIL = "INSERT INTO detail(name,type) VALUES(?,?)";
    public static final String GET_DETAIL = "SELECT * FROM detail WHERE name = ? AND type = ?";

    public void createDetail(Detail detail) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_DETAIL);
            statement.setString(1, detail.getDetailName());
            statement.setString(2, detail.getDetailType());
            statement.executeUpdate();
        }
    }
    public Detail getDetail(Detail detail) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_DETAIL);
            statement.setString(1, detail.getDetailName());
            statement.setString(2, detail.getDetailType());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Detail(resultSet.getString("type"),
                    resultSet.getString("name"),
                    resultSet.getInt("id"));
        }
    }



}
