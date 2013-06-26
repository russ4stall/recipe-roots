package com.github.russ4stall.reciperoots.users.dao;

import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.sqlUtilities;
import com.google.common.base.Objects;

import java.sql.*;

/**
 * Date: 6/18/13
 * Time: 10:02 AM
 *
 * @author Russ Forstall
 */
public class UsersDaoImpl implements UsersDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public boolean emailExistsCheck(String email) {
        boolean emailExists = false;

        sqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT count(1) FROM users WHERE email=?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            emailExists = resultSet.getInt("count(1)") == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            sqlUtilities.closePreparedStatement(preparedStatement);
            sqlUtilities.closeResultSet(resultSet);
            sqlUtilities.closeConnection(connection);
        }
        return emailExists;
    }

    @Override
    public void addUser(String name, String email, String password) {

        sqlUtilities.jbdcUtil();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");
            String query = "INSERT INTO users (name, email, password, registered_on, last_login_on) " +
                    "VALUES (?, ?, ?, now(), now())";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            sqlUtilities.closePreparedStatement(preparedStatement);
            sqlUtilities.closeConnection(connection);
        }

    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        sqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            query = "SELECT password, id, name, email FROM users WHERE email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));


            if (Objects.equal(password, user.getPassword())){
                query = "UPDATE users SET last_login_on = now() WHERE email=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            sqlUtilities.closePreparedStatement(preparedStatement);
            sqlUtilities.closeConnection(connection);
            sqlUtilities.closeResultSet(resultSet);
        }

            return user;
    }
}
