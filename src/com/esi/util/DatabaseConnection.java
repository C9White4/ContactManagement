package com.esi.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.nio.file.Paths;
import java.sql.SQLException;
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String URL="jdbc:sqlite:"+Paths.get("Database&Files", "esi_db").toString();
    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new SQLException("Database connection error",e);
        }
    }
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance==null) {
            instance=new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}