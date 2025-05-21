package com.mediconnect.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database configuration class for establishing connection to MySQL database
 */
public class Dbconfig {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/mediconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    /**
     * Establishes and returns a database connection
     * @return Connection object
     * @throws SQLException if database access error occurs
     * @throws ClassNotFoundException if JDBC driver not found
     */
    public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Create and return connection
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}