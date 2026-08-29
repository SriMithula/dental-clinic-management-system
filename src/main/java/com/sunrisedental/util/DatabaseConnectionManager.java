package com.sunrisedental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static DatabaseConnectionManager singleInstance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/SunriseDentalClinic";
    private final String username = "root";
    private final String password = "1234";

    private DatabaseConnectionManager() {


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                url,
                username,
                password
            );

            System.out.println("Database connected successfully!");
            System.out.println("Connection: " + connection);

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }
    }

    public static DatabaseConnectionManager getInstance() {

        if (singleInstance == null) {
            singleInstance = new DatabaseConnectionManager();
        }

        return singleInstance;
    }

    public Connection getConnection() {

        return connection;
    }
}