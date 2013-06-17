package com.github.russ4stall.reciperoots;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 6/13/13
 * Time: 9:18 AM
 *
 * @author Russ Forstall
 */
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink = null;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("/login?validUser=true");
            return;
        }else{
            logInLink = "<a href=\"/logout\">Log Out</a>";
        }

        req.setAttribute("logInLink", logInLink);

        User user = (User) session.getAttribute("user");

        String sIsNew = req.getParameter("isNew");
        boolean isNew = Boolean.parseBoolean(sIsNew);


        String recipeField = null;

        if(!isNew){
            String sRecipeId = req.getParameter("id");
            int recipeId = Integer.valueOf(sRecipeId);

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            Recipe recipe = new Recipe();

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
                    recipe.setRecipe(resultSet.getString("recipe"));//get recipe string from db
                    recipe.setCreatedOn(resultSet.getDate("created_on"));//get date from db
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


            req.setAttribute("recipeTitle", recipe.getTitle());
            recipeField = recipe.getRecipe();

        } else {

            recipeField = "<p>This is where the description your recipe goes.</p> \n" +
                    "<p>List of ingredients:</p>\n" +
                    "<li>ingredient</li>\n<li>ingredient</li>\n<li>ingredient</li>\n<li>ingredient</li>\n" +
                    "<li>ingredient</li>\n<li>ingredient</li>\n<p>Anything else you wanna add.</p>";
        }
        req.setAttribute("recipeField", recipeField);
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sIsNew = req.getParameter("isNew");
        boolean isNew = Boolean.parseBoolean(sIsNew);

        String recipeText = req.getParameter("recipe");
        String recipeTitle = req.getParameter("title");

        Date date = new Date();
        String query = null;

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        Connection connection = null;
        PreparedStatement preparedStatement = null;


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

            if(isNew){

                query = "INSERT INTO recipes (title, recipe, user_id, created_on) " +
                        "VALUES (?, ?, ?, now())";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,recipeTitle);
                preparedStatement.setString(2,recipeText);
                preparedStatement.setInt(3, user.getId());
                preparedStatement.executeUpdate();


            }else {
               String sId = req.getParameter("id");
               int id = Integer.valueOf(sId);

                query = "UPDATE recipes " +
                        "SET title=?, recipe=?, created_on=now()" +
                        "WHERE id=?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,recipeTitle);
                preparedStatement.setString(2,recipeText);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

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

        resp.sendRedirect("/myrecipes");
    }
}
