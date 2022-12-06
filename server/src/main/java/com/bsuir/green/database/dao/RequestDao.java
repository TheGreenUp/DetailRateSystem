package com.bsuir.green.database.dao;

import com.bsuir.green.common.command.getCommands.GetClientIdFromRequestCommand;
import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.model.RequestForStuff;
import com.bsuir.green.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    private static final RequestDao requestDao = new RequestDao();

    //region SQL Команды
    public static final String GET_BY_ID = "SELECT * FROM request WHERE id = ?";
    public static final String UPDATE_REQUEST_STATUS = "UPDATE request SET status = 'Проверена' WHERE request.id = ?";
    public static final String GET_ALL = "SELECT * FROM request";
    public static final String GET_ALL_FOR_STUFF = "SELECT * FROM request " +
            " JOIN autosalon.detail ON detail_id = autosalon.detail.id " +
            " JOIN autosalon.client ON client_id = autosalon.client.id" +
            " WHERE status = 'В процессе' AND stuff_id = ?";
    public static final String CREATE_REQUEST = "INSERT INTO request(detail_id,stuff_id,client_id) VALUES(?,?,?)";
    public static final String GET_CLIENT_BY_REQUEST_ID = "SELECT * FROM autosalon.request" +
            " JOIN autosalon.client ON client_id = client.id WHERE request.id = ?";
    public static final String GET_ALL_CLIENT_DETAILS = "SELECT * FROM autosalon.request" +
            " JOIN autosalon.detail ON detail_id = detail.id WHERE client_id = ?";
    //endregion

    public static RequestDao getInstance() {
        return requestDao;
    }
    private final ConnectionManager connectionManager;


    private RequestDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    public void updateRequestStatus(int requestId) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST_STATUS);
            statement.setInt(1, requestId);
            statement.executeUpdate();
        }
    }

    public Request getById(Integer id) throws SQLException {//
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Request(resultSet.getString("status"),
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("detail_id"),
                        resultSet.getInt("stuff_id"));
            } else {
                throw new UserNotFoundException("Failed to find user by id " + id);
            }
        }
    }

    public List<RequestForStuff> getInfoForStuff(int stuff_id) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_FOR_STUFF);
            statement.setInt(1, stuff_id);
            ResultSet resultSet = statement.executeQuery();
            List<RequestForStuff> requests = new ArrayList<>();
            while (resultSet.next()) {
                RequestForStuff requestForStuff = new RequestForStuff(
                        resultSet.getInt("id"),
                        resultSet.getInt("client.id"),
                        resultSet.getInt("detail.id"),
                        resultSet.getString("clientName"),
                        resultSet.getString("clientSurname"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("status"));
                requests.add(requestForStuff);
            }
            return requests;
        }
    }
    public ArrayList<Request> getAll() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            ArrayList<Request> requests = new ArrayList<>();

            while (resultSet.next()) {
                Request request = new Request(resultSet.getString("status"),
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("detail_id"),
                        resultSet.getInt("stuff_id"));
                requests.add(request);
            }
            return requests;
        }
    }

    public void createRequest(Request request) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_REQUEST);
            statement.setInt(1, request.getDetail_id());
            statement.setInt(2, request.getStuff_id());
            statement.setInt(3, request.getClient_id());
            statement.executeUpdate();
        }
    }
    public Client getClientByRequest(GetClientIdFromRequestCommand getClientIdFromRequestCommand) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_CLIENT_BY_REQUEST_ID);
            statement.setInt(1, getClientIdFromRequestCommand.getRequestId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Client(
                    resultSet.getInt("client.id"),
                    resultSet.getString("clientSurname"),
                    resultSet.getString("clientName"),
                    resultSet.getString("clientEmail"),
                    resultSet.getString("clientPassword"));
        }
    }
    public ArrayList<Detail> getAllClientDetails(Client client) throws SQLException{
        ArrayList<Detail> details = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_CLIENT_DETAILS);
            statement.setInt(1, client.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Detail detail = new Detail(resultSet.getString("type"),
                        resultSet.getString("name"),
                        resultSet.getInt("detail.id"));
                details.add(detail);
            }
        }
        return details;
    }
}

