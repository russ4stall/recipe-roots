package com.github.russ4stall.reciperoots;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 6/12/13
 * Time: 1:36 PM
 *
 * @author Russ Forstall
 */
public class ViewRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sRecipeId = req.getParameter("id");
        int recipeId = Integer.valueOf(sRecipeId);

        Recipe recipe = new Recipe();
        User user = new User();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load the MySQL driver!", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipes", "recipe", "recipe");

            preparedStatement = connection.prepareStatement("SELECT * FROM recipes WHERE id=?");

            preparedStatement.setInt(1, recipeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                recipe.setTitle(resultSet.getString("title"));//get title from db
                user.setId(resultSet.getInt("user_id"));
                recipe.setUser(user);
                recipe.setRecipe(resultSet.getString("recipe"));//get recipe string from db
                recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
                recipe.setId(resultSet.getInt("id"));
            }

            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // don't care
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // don't care
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // don't care
                }
            }
        }




        req.setAttribute("recipe", recipe);

        req.getRequestDispatcher("/WEB-INF/jsp/viewrecipe.jsp").forward(req, resp);
    }
}
