package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        final String dbUser = "root";
        final String dbPassword = "password";
        return getConnection(dbUser, dbPassword);
    }

    public static Connection getConnection(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException  {
        final String DBNAME = "DBTEST";
        final String CREATE_DATABASE = "CREATE DATABASE " + DBNAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Драйвер подключен");
        String connectionURL = "jdbc:mysql://localhost:3306/DBTEST";
        Connection connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
        System.out.println("Соединение установлено");
//        Statement statement = connection.createStatement();
//        statement.execute(CREATE_DATABASE);
//        System.out.println("База данных создана");
//        statement.close();
        return connection;
    }
}
