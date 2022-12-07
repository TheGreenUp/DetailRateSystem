package com.bsuir.green.database.dao;


import com.bsuir.green.common.command.CheckStuffExistCommand;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;

public final class StuffDao {

    private static final StuffDao stuffDao = new StuffDao();

    //region SQL команды
    public static final String DELETE_STUFF = "DELETE FROM stuff WHERE id = ? AND stuffEmail = ?";
    public static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM stuff WHERE stuffEmail = ? AND stuffPassword = ?";
    public static final String GET_BY_ID = "SELECT * FROM stuff WHERE id = ?";
    public static final String ADD_USER_SQL = "INSERT INTO stuff(stuffName, stuffSurname, stuffEmail, stuffPassword, stuffRole) VALUES(?,?,?,?,?)";
    public static final String GET_ALL = "SELECT * FROM stuff";
    public static final String GET_SPECIALISTS = "SELECT * FROM stuff WHERE stuffRole != 0";
    public static final String CHECK_STUFF_EXIST = "SELECT * FROM stuff WHERE stuffEmail = ?";
    public static final String UPDATE_BY_ID =
            "UPDATE stuff SET stuffName = ?, stuffSurname = ?, stuffEmail = ?, stuffPassword = ?, stuffRole = ? where id = ?";
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
            statement.setInt(5, stuff.getRole());
            statement.setInt(6, stuff.getId());
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
                        resultSet.getInt("stuffRole"),
                        resultSet.getString("stuffSurname"),
                        resultSet.getString("stuffName"),
                        resultSet.getString("stuffEmail"),
                        resultSet.getString("stuffPassword"));
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
                        resultSet.getInt("stuffRole"),
                        resultSet.getString("stuffSurname"),
                        resultSet.getString("stuffName"),
                        resultSet.getString("stuffEmail"),
                        resultSet.getString("stuffPassword"));
            } else {
                throw new UserNotFoundException("Failed to find user by email " + email + " and password " + password);
            }
        }
    }

    public ArrayList<Stuff> getAll() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            ArrayList<Stuff> stuffList = new ArrayList<>();

            while (resultSet.next()) {
                Stuff stuff =new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("stuffRole"),
                        resultSet.getString("stuffSurname"),
                        resultSet.getString("stuffName"),
                        resultSet.getString("stuffEmail"),
                        resultSet.getString("stuffPassword"));
                stuffList.add(stuff);
            }
            return stuffList;
        }
    }
    public ArrayList<Stuff> getSpecialists() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_SPECIALISTS);
            ArrayList<Stuff> stuffList = new ArrayList<>();
            while (resultSet.next()) {
                Stuff stuff =new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("stuffRole"),
                        resultSet.getString("stuffSurname"),
                        resultSet.getString("stuffName"),
                        resultSet.getString("stuffEmail"),
                        resultSet.getString("stuffPassword"));
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
    public boolean checkStuffExist(CheckStuffExistCommand command) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CHECK_STUFF_EXIST);
            statement.setString(1, command.getStuff().getEmail());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
}

