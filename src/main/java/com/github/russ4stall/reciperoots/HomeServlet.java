package com.github.russ4stall.reciperoots;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/12/13
 * Time: 12:13 PM
 *
 * @author Russ Forstall
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get recipes from the db to post on the Home page
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

        List<Recipe> recipeList = new ArrayList<Recipe>();

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

            preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM recipes " +
                    "JOIN users " +
                    "ON recipes.user_id = users.id " +
                    "ORDER BY recipes.created_on");
            resultSet = preparedStatement.executeQuery();


            //List users = new ArrayList();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                User user = new User();
                recipe.setTitle(resultSet.getString("title"));//get title from db
                recipe.setUser(user);
                recipe.setId(resultSet.getInt("id"));//get id from db
                recipe.setRecipe(resultSet.getString("recipe"));//get recipe string from db
                recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
                user.setName(resultSet.getString("name"));
                user.setRegisteredOn(resultSet.getDate("registered_on"));
                user.setLastLoginOn(resultSet.getDate("last_login_on"));
                user.setEmail(resultSet.getString("email"));
                recipeList.add(recipe);

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


        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1 = recipeList.get((int) (Math.random()* recipeList.size()));
        recipe2 = recipeList.get(recipeList.size()-1);
        req.setAttribute("recipe1", recipe1);
        req.setAttribute("recipe2", recipe2);


        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
