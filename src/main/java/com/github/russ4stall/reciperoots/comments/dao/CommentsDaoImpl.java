package com.github.russ4stall.reciperoots.comments.dao;

import com.github.russ4stall.reciperoots.comments.Comment;
import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/21/13
 * Time: 11:09 AM
 *
 * @author Russ Forstall
 */
public class CommentsDaoImpl implements CommentsDao{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public void addComment(String comment, int userId, int recipeId) {
        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");


            String query = "INSERT INTO comments (user_id, recipe_id, comment, created_on) " +
                    "VALUES (?, ?, ?, now())";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, recipeId);
            preparedStatement.setString(3, comment);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
        }
    }




    @Override
    public void deleteComment(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List getAllComments(int recipeId) {
        List<Comment> commentList = new ArrayList();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM comments " +
                    "JOIN users " +
                    "ON comments.user_id = users.id " +
                    "WHERE recipe_id = ? " +
                    "ORDER BY comments.created_on DESC");
            preparedStatement.setInt(1, recipeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                User user = new User();
                comment.setUser(user);
                comment.setId(resultSet.getInt("id"));
                comment.setRecipeId(resultSet.getInt("recipe_id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setCreatedOn(resultSet.getDate("created_on"));
                user.setName(resultSet.getString("name"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("users.id"));
                commentList.add(comment);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }

        return commentList;
    }  //To change body of implemented methods use File | Settings | File Templates.


}