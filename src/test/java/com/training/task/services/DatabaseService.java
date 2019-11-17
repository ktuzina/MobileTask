package com.training.task.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class DatabaseService {

    private static Long suiteID;
    private static final String DATABASE_PROPERTIES = "database.properties";

    private static Properties pr = new Properties();

    static {
        try {
            FileInputStream inp = new FileInputStream(Objects.requireNonNull(DatabaseService.class.getClassLoader().getResource(DATABASE_PROPERTIES)).getPath());
            pr.load(inp);
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String databaseURL = pr.getProperty("dbURL");
    private static String user = pr.getProperty("user");
    private static String password = pr.getProperty("password");

    public static void insertSuiteInfo() {
        String query = "INSERT INTO suite_results VALUES (NULL, NULL, NULL, NULL)";
        suiteID = null;
        try (
                Connection c = DriverManager.getConnection(databaseURL, user, password);
                PreparedStatement prState = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = prState.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("insert failed");
            }
            try (ResultSet generatedKeys = prState.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    suiteID = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating suite failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSuiteInfo(int count, String suiteName, long time) {
        String query = "UPDATE suite_results SET suite_name = ?, tests_count = ?, execution_time = ? WHERE id = ?;";
        try (
                Connection c = DriverManager.getConnection(databaseURL, user, password);
                PreparedStatement prState = c.prepareStatement(query)) {
            prState.setString(1, suiteName);
            prState.setInt(2, count);
            prState.setLong(3, time);
            prState.setLong(4, suiteID);

            prState.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTestInfo(String testName, String result) {
        String query = "INSERT INTO test_result VALUES (NULL, ?, ?, ?);";
        try (
                Connection c = DriverManager.getConnection(databaseURL, user, password);
                PreparedStatement prState = c.prepareStatement(query)) {
            prState.setLong(1, suiteID);
            prState.setString(2, testName);
            prState.setString(3, result);
            prState.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
