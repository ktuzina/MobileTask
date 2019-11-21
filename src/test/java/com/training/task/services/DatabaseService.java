package com.training.task.services;

import java.sql.*;

public class DatabaseService {

    private static Long suiteID;

    public synchronized static void insertSuiteInfo() {
        Connection c = DbConnectionService.getConnection();
        String query = "INSERT INTO suite_results VALUES (NULL, NULL, NULL, NULL)";
        suiteID = null;
        try (
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

    public synchronized static void updateSuiteInfo(int count, String suiteName, long time) {
        Connection c = DbConnectionService.getConnection();
        String query = "UPDATE suite_results SET suite_name = ?, tests_count = ?, execution_time = ? WHERE id = ?;";
        try (
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

    public synchronized static void insertTestInfo(String testName, String result) {
        Connection c = DbConnectionService.getConnection();
        String query = "INSERT INTO test_result VALUES (NULL, ?, ?, ?);";
        try (
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
