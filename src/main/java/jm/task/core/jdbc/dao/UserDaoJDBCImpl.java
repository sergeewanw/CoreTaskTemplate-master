package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    private Statement statement = connection.createStatement();
    private final String tableName = "users";

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        final String CREATE_TABLE = "CREATE TABLE " + tableName + " (id INT AUTO_INCREMENT, name VARCHAR(256) NOT NULL, lastName VARCHAR(256) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY(id))";
        try {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) { }
    }

    public void dropUsersTable() {
        final String DROP_TABLE = "DROP TABLE " + tableName;
        final String SHOW_TABLES = "SHOW TABLES LIKE '" + tableName + "'";
        try {
            boolean arrayShowTables = statement.execute(SHOW_TABLES);
            if (arrayShowTables) {
                statement.execute(DROP_TABLE);
            }
        } catch (SQLException e) { }
    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT_INTO = "INSERT INTO " + tableName + " SET name = '" + name + "', lastName = '" + lastName + "', age = '" + age + "'";
        try {
            statement.execute(INSERT_INTO);
        }catch (SQLException e) { }
    }

    public void removeUserById(long id) {
        final String DELETE_FROM = "DELETE FROM " + tableName + " WHERE id = " + id;
        try {
            statement.execute(DELETE_FROM);
        } catch (SQLException e) { }
    }

    public List<User> getAllUsers() {
        final String SELECT_FROM = "SELECT * FROM " + tableName;
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM);
            ResultSet query = preparedStatement.executeQuery();
            while (query.next()) {
                list.add(new User(query.getString("name"), query.getString("lastName"), query.getByte("age")));
            }
        }catch (SQLException e) { }
        return list;
    }

    public void cleanUsersTable() {
        final String TRUNCATE_TABLE = "TRUNCATE TABLE " + tableName;
        try {
            statement.execute(TRUNCATE_TABLE);
        }catch (SQLException e) { }
    }
}
