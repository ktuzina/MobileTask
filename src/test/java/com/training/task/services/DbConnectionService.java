package com.training.task.services;

import com.training.task.utils.PropertyHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionService {

    private static Connection c;

    public static Connection getConnection() {
        if (c == null) {
            try {
                c = DriverManager.getConnection(PropertyHandler.getValue("dbURL"),
                        PropertyHandler.getValue("user"), PropertyHandler.getValue("password"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return c;
    }

    public static void closeConnection() {
        try {
            if (c != null)
                c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
