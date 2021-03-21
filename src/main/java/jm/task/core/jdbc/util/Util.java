package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final String DB_NAME = "schema";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static Connection connection;

    public static Connection getConnection() {
        try  {
            connection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
