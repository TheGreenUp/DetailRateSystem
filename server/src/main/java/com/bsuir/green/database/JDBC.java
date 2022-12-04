package com.bsuir.green.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBC {
    public static final String URL = "jdbc:mysql://localhost:3306/autosalon";
    public static final  String USER = "root";
    public static final String PASSWORD = "88102010ValekA";
    public static Connection connection = null;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            throw new SQLException();
        }
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        if (connection == null) {
            throw new SQLException();
        } else {
            System.out.println("Succesfully connected!");
        }
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }

}


