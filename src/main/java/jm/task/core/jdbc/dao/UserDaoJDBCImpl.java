package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT, name VARCHAR(256) NOT NULL, lastName VARCHAR(256) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY(id));");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("INSERT INTO users SET name = '" + name + "', lastName = '" + lastName + "', age = '" + age + "';");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DELETE FROM users WHERE id = " + id + ";");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM users;")) {
            ResultSet query = preparedStatement.executeQuery();
            while (query.next()) {
                list.add(new User(query.getString("name"), query.getString("lastName"), query.getByte("age")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("TRUNCATE TABLE users;");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
