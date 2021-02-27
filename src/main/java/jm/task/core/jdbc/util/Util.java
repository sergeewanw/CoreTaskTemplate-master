package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        final String DB_USER = "root";
        final String DB_PASSWORD = "password";
        return getConnection(DB_USER, DB_PASSWORD);
    }

    public static Connection getConnection(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException  {
        final String DB_NAME = "DBTEST";
        final String CREATE_DATABASE = "CREATE DATABASE " + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Драйвер подключен");
        String connectionURL = "jdbc:mysql://localhost:3306/" + DB_NAME;
        Connection connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
        System.out.println("Соединение установлено");
        return connection;
    }
}
