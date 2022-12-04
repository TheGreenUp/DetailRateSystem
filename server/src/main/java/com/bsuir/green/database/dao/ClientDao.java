package com.bsuir.green.database.dao;


import com.bsuir.green.common.model.Client;
import com.bsuir.green.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ClientDao {

    private static final ClientDao clientDao = new ClientDao();

    public static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM client WHERE email = ? AND password = ?";
    public static final String GET_BY_ID = "SELECT * FROM client WHERE id = ?";
    public static final String ADD_USER_SQL = "INSERT INTO client(fname, lname, email, password) VALUES(?,?,?,?)";
    public static final String GET_ALL = "SELECT * FROM client";
    public static final String UPDATE_BY_ID =
            "UPDATE client SET fname = ?, lname = ?, email = ?, password = ? where id = ?";

    private final ConnectionManager connectionManager;

    public static ClientDao getInstance() {
        return clientDao;
    }

    private ClientDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    public void update(Client client) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, client.getFname());
            statement.setString(2, client.getLname());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPassword());
            statement.setInt(5, client.getId());
            statement.executeUpdate();
        }
    }

    public Client getById(Integer id) throws SQLException {//
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Client(resultSet.getInt("id"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            } else {
                throw new UserNotFoundException("Failed to find user by id " + id);
            }
        }
    }

    public Client getByEmailAndPassword(String email, String password) throws SQLException {//   "SELECT * FROM stuff WHERE email = ? AND password = ?";

        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Client(resultSet.getInt("id"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            } else {
                throw new UserNotFoundException("Failed to find user by email " + email + " and password " + password);
            }
        }
    }

    public List<Client> getAll() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            List<Client> clientList = new ArrayList<>();

            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("id"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                clientList.add(client);
            }
            return clientList;
        }
    }

    public void createClient(Client client) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL);
            statement.setString(1, client.getFname());
            statement.setString(2, client.getLname());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPassword());
            statement.executeUpdate();
        }
    }
}


