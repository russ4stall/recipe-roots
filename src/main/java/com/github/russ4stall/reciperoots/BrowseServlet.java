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
 * Date: 6/13/13
 * Time: 3:38 PM
 *
 * @author Russ Forstall
 */
public class BrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logInLink;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
           logInLink = "<a href=\"/login?validUser=true\">Log In</a>";
        }else{
           logInLink = "<a href=\"/logout\">Log Out</a>";
        }
        req.setAttribute("logInLink", logInLink);



        //get users recipes and display them
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
                                                            "ORDER BY recipes.title");
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

      /*  List<Recipe> recipeList = new ArrayList<Recipe>();

        User user = new User();
        user.setId(1);
        user.setEmail("thisguy@thisplace.com");
        user.setPassword("fluffybunny");
        user.setRegisteredOn(new Date());
        user.setName("This Guy");

        for (int i = 0; i < 5; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle("Honey Brew");//get title from db
            recipe.setUser(user);//get user from db or current user? (see above)
            recipe.setId(1);//get id from db
            recipe.setRecipe(thisRecipe);//get recipe string from db
            recipe.setCreatedOn(new Date());//get date from db

            recipeList.add(recipe);
        }
*/
        req.setAttribute("recipes", recipeList);


        req.getRequestDispatcher("/WEB-INF/jsp/browse.jsp").forward(req, resp);
    }


}
