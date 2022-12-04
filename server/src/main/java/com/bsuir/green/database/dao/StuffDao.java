package com.bsuir.green.database.dao;


import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class StuffDao {

    private static final StuffDao stuffDao = new StuffDao();

    //region SQL команды
    public static final String DELETE_STUFF = "DELETE FROM stuff WHERE id = ? AND email = ?";
    public static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM stuff WHERE email = ? AND password = ?";
    public static final String GET_BY_ID = "SELECT * FROM stuff WHERE id = ?";
    public static final String ADD_USER_SQL = "INSERT INTO stuff(fname, lname, email, password, role) VALUES(?,?,?,?,?)";
    public static final String GET_ALL = "SELECT * FROM stuff";
    public static final String UPDATE_BY_ID =
            "UPDATE stuff SET fname = ?, lname = ?, email = ?, password = ? where id = ?";
    //endregion

    private final ConnectionManager connectionManager;

    public static StuffDao getInstance() {
        return stuffDao;
    }

    private StuffDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    public void updateStuff(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, stuff.getFname());
            statement.setString(2, stuff.getLname());
            statement.setString(3, stuff.getEmail());
            statement.setString(4, stuff.getPassword());
            statement.setInt(5, stuff.getId());
            statement.executeUpdate();
        }
    }
    public void deleteStuff(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUFF);
            statement.setInt(1, stuff.getId());
            statement.setString(2, stuff.getEmail());
            statement.executeUpdate();
        }
    }

    public Stuff getById(Integer id) throws SQLException {//
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("role"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            } else {
                throw new UserNotFoundException("Failed to find user by id " + id);
            }
        }
    }

    public Stuff getByEmailAndPassword(String email, String password) throws SQLException {//   "SELECT * FROM stuff WHERE email = ? AND password = ?";

        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("role"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            } else {
                throw new UserNotFoundException("Failed to find user by email " + email + " and password " + password);
            }
        }
    }

    public List<Stuff> getAll() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            List<Stuff> stuffList = new ArrayList<>();

            while (resultSet.next()) {
                Stuff stuff = new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("role"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));;
                stuffList.add(stuff);
            }
            return stuffList;
        }
    }

    public void createUser(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL);
            statement.setString(1, stuff.getFname());
            statement.setString(2, stuff.getLname());
            statement.setString(3, stuff.getEmail());
            statement.setString(4, stuff.getPassword());
            statement.setInt(5, stuff.getRole());
            statement.executeUpdate();
        }
    }
}

