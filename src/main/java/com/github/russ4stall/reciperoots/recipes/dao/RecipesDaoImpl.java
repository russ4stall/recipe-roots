package com.github.russ4stall.reciperoots.recipes.dao;

import com.github.russ4stall.reciperoots.recipes.Recipe;
import com.github.russ4stall.reciperoots.users.User;
import com.github.russ4stall.reciperoots.utilities.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/17/13
 * Time: 4:06 PM
 *
 * @author Russ Forstall
 */
public class RecipesDaoImpl implements RecipesDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void updateRecipe(int id, String title, String recipe) {
        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");


            String query = "UPDATE recipes " +
                    "SET title=?, recipe=?, created_on=now()" +
                    "WHERE id=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, recipe);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
        }
    }


    @Override
    public void addRecipe(String title, String recipe, int userId) {
        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");


            String query = "INSERT INTO recipes (title, recipe, user_id, created_on) " +
                    "VALUES (?, ?, ?, now())";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,recipe);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
        }
    }


    @Override
    public void deleteRecipe(int id) {
        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            String query = "DELETE FROM recipes WHERE id = ?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public Recipe getRecipe(int id) {
        Recipe recipe = new Recipe();
        User user = new User();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * FROM recipes " +
                    "JOIN users ON recipes.user_id = users.id WHERE recipes.id=?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

           resultSet.next();
                recipe.setTitle(resultSet.getString("title"));//get title from db
                recipe.setId(resultSet.getInt("id"));
                recipe.setUser(user);
                recipe.setRecipe(resultSet.getString("recipe"));//get recipes string from db
                recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }


        return recipe;
    }

    @Override
    public List getAllRecipes() {
        List<Recipe> recipeList = new ArrayList();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM recipes " +
                    "JOIN users " +
                    "ON recipes.user_id = users.id " +
                    "ORDER BY recipes.title");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                User user = new User();
                recipe.setTitle(resultSet.getString("title"));//get title from db
                recipe.setUser(user);
                recipe.setId(resultSet.getInt("id"));//get id from db
                recipe.setRecipe(resultSet.getString("recipe"));//get recipes string from db
                recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
                user.setName(resultSet.getString("name"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                recipeList.add(recipe);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }

        return recipeList;
    }

    @Override
    public List getMyRecipes(int userId) {
        List<Recipe> recipeList = new ArrayList();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM recipes " +
                    "JOIN users " +
                    "ON recipes.user_id = users.id " +
                    "WHERE user_id=? " +
                    "ORDER BY recipes.title");

            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                User user = new User();
                recipe.setTitle(resultSet.getString("title"));//get title from db
                recipe.setUser(user);
                recipe.setId(resultSet.getInt("id"));//get id from db
                recipe.setRecipe(resultSet.getString("recipe"));//get recipes string from db
                recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
                user.setName(resultSet.getString("name"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }

        return recipeList;
    }

    @Override
    public Recipe getRandomRecipe() {
        Recipe recipe = new Recipe();
        User user = new User();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * FROM recipes " +
                    "JOIN users ON recipes.user_id = users.id ORDER BY RAND() LIMIT 1");


            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            recipe.setTitle(resultSet.getString("title"));//get title from db
            user.setId(resultSet.getInt("user_id"));
            recipe.setId(resultSet.getInt("id"));
            recipe.setUser(user);
            recipe.setRecipe(resultSet.getString("recipe"));//get recipes string from db
            recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setLastLoginOn(resultSet.getDate("last_login_on"));
            user.setRegisteredOn(resultSet.getDate("registered_on"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }

        return recipe;
    }

    @Override
    public Recipe getLatestRecipe() {
        Recipe recipe = new Recipe();
        User user = new User();

        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * FROM recipes " +
                    "JOIN users ON recipes.user_id = users.id ORDER BY recipes.created_on DESC LIMIT 1");


            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            recipe.setTitle(resultSet.getString("title"));//get title from db
            user.setId(resultSet.getInt("user_id"));
            recipe.setId(resultSet.getInt("id"));
            recipe.setUser(user);
            recipe.setRecipe(resultSet.getString("recipe"));//get recipes string from db
            recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setLastLoginOn(resultSet.getDate("last_login_on"));
            user.setRegisteredOn(resultSet.getDate("registered_on"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
        }

        return recipe;
    }
}


